package com.casual.drawing.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

/**
 * @author 王川源
 * 图像的持久化对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ImagePO {

    /**
     * 图像名
     */
    @Id
    private String imgName;

    /**
     * 图像的base64码
     */
    @Lob
    private String base64Str;

    /**
     * 最后一次修改时间
     */
    private LocalDateTime time;
}
