package CyberHanryang.dto;

import CyberHanryang.entity.Emoji;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmojiStatDto {
    private Emoji emoji;
    private Long count;

    public EmojiStatDto(Emoji emoji, Long count) {
        this.emoji = emoji;
        this.count = count;
    }

    public boolean compare(Emoji emoji) {
        if (this.getEmoji().getName().equals(emoji.getName())) {
            return this.getEmoji().getTag().equals(emoji.getTag());
        }
        return false;
    }

    public String getRealEmojiContent() {
        return "<:" + emoji.getName() + ":" + emoji.getTag() + ">";
    }
}
