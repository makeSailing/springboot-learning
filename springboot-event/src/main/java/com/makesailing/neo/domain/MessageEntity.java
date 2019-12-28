package com.makesailing.neo.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2019/6/21 11:34
 */
@Data
@Builder
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 6556551711262224052L;

    private String message;

    private String code;
}

