package com.makesailing.neo.config;

import java.util.Objects;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/12 0:10
 */
public class RedisObjectSeralizable implements RedisSerializer<Object> {

  private Converter<Object, byte[]> serializer = new SerializingConverter();
  private Converter<byte[], Object> deserializer = new DeserializingConverter();


  static final byte[] EMPTY_APPAY = new byte[0];


  @Override
  public byte[] serialize(Object o) throws SerializationException {
    if (Objects.isNull(o)) {
      return EMPTY_APPAY;
    }
    try {
      return serializer.convert(o);
    } catch (Exception e) {
      return EMPTY_APPAY;
    }
  }

  @Override
  public Object deserialize(byte[] bytes) throws SerializationException {
    if (isEmpty(bytes)) {
      return null;
    }
    try {
      return deserializer.convert(bytes);
    } catch (Exception e) {
      throw new SerializationException("Cannot deserializer", e);
    }
  }

  private boolean isEmpty(byte[] data) {
    return (data == null || data.length == 0);
  }
}
