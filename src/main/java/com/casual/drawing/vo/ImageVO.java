package com.casual.drawing.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王川源
 * 图像的值对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageVO {

    /**
     * 图像的base64码
     */
    private String base64Str;
}
