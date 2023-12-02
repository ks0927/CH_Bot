package CyberHanryang.service;

import CyberHanryang.dto.EmojiStatDto;
import CyberHanryang.entity.Emoji;
import CyberHanryang.entity.Server;
import CyberHanryang.repository.EmojiRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmojiService {
    private final EmojiRepository emojiRepository;

    public List<EmojiStatDto> getCurrentServerEmojiStat(Server server, List<Emoji> serverEmojis) {
        List<EmojiStatDto> emojiStat = new ArrayList<>();
        // serverEmojis의 모든 이모지를 0의 값으로 초기화
        for (Emoji emoji : serverEmojis) {
            emojiStat.add(new EmojiStatDto(emoji, 0L));
        }

        List<Emoji> usingEmojis = emojiRepository.findByServer(server);

        for (Emoji usingEmoji : usingEmojis) {
            for (EmojiStatDto dto : emojiStat) {
                if (dto.compare(usingEmoji)) {
                    dto.setCount(dto.getCount() + 1);
                    break;
                }
            }
        }

        // count를 기준으로 내림차순 정렬
        emojiStat.sort((dto1, dto2) -> dto2.getCount().compareTo(dto1.getCount()));

        return emojiStat;
    }
}
