package org.infinispan.client.hotrod.impl.operations;

import java.util.concurrent.atomic.AtomicInteger;

import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.impl.protocol.Codec;
import org.infinispan.client.hotrod.impl.protocol.HeaderParams;
import org.infinispan.client.hotrod.impl.protocol.HotRodConstants;
import org.infinispan.client.hotrod.impl.transport.netty.ChannelFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import net.jcip.annotations.Immutable;

/**
 * Implements "get" operation as described by <a href="http://community.jboss.org/wiki/HotRodProtocol">Hot Rod protocol specification</a>.
 *
 * @author Mircea.Markus@jboss.com
 * @since 4.1
 */
@Immutable
public class GetOperation<V> extends AbstractKeyOperation<V> {

   public GetOperation(Codec codec, ChannelFactory channelFactory,
                       Object key, byte[] keyBytes, byte[] cacheName, AtomicInteger topologyId, int flags,
                       Configuration cfg) {
      super(codec, channelFactory, key, keyBytes, cacheName, topologyId, flags, cfg);
   }

   @Override
   public void executeOperation(Channel channel) {
      HeaderParams header = headerParams(GET_REQUEST);
      scheduleRead(channel, header);
      sendArrayOperation(channel, header, keyBytes);
   }

   @Override
   public V decodePayload(ByteBuf buf, short status) {
      if (!HotRodConstants.isNotExist(status) && HotRodConstants.isSuccess(status)) {
         return codec.readUnmarshallByteArray(buf, status, cfg.serialWhitelist(), channelFactory.getMarshaller());
      } else {
         return null;
      }
   }
}
