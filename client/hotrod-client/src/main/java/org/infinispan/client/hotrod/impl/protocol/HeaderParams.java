package org.infinispan.client.hotrod.impl.protocol;

import java.util.concurrent.atomic.AtomicInteger;

import org.infinispan.client.hotrod.Flag;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;

/**
 * Hot Rod request header parameters
 *
 * @author Galder Zamarreño
 * @since 5.1
 */
public class HeaderParams {

   short opCode;
   short opRespCode;
   byte[] cacheName;
   int flags;
   byte clientIntel;
   byte txMarker;
   AtomicInteger topologyId;
   long messageId;
   int topologyAge;

   public HeaderParams opCode(short opCode) {
      this.opCode = opCode;
      this.opRespCode = toOpRespCode(opCode);
      return this;
   }

   public HeaderParams cacheName(byte[] cacheName) {
      this.cacheName = cacheName;
      return this;
   }

   public HeaderParams flags(int flags) {
      this.flags = flags;
      return this;
   }

   public HeaderParams clientIntel(ClientIntelligence clientIntel) {
      this.clientIntel = clientIntel.getValue();
      return this;
   }

   public HeaderParams txMarker(byte txMarker) {
      this.txMarker = txMarker;
      return this;
   }

   public HeaderParams topologyId(AtomicInteger topologyId) {
      this.topologyId = topologyId;
      return this;
   }

   public HeaderParams messageId(long messageId) {
      this.messageId = messageId;
      return this;
   }

   public HeaderParams topologyAge(int topologyAge) {
      this.topologyAge = topologyAge;
      return this;
   }

   protected short toOpRespCode(short opCode) {
      switch (opCode) {
         case HotRodConstants.PUT_REQUEST:
            return HotRodConstants.PUT_RESPONSE;
         case HotRodConstants.GET_REQUEST:
            return HotRodConstants.GET_RESPONSE;
         case HotRodConstants.PUT_IF_ABSENT_REQUEST:
            return HotRodConstants.PUT_IF_ABSENT_RESPONSE;
         case HotRodConstants.REPLACE_REQUEST:
            return HotRodConstants.REPLACE_RESPONSE;
         case HotRodConstants.REPLACE_IF_UNMODIFIED_REQUEST:
            return HotRodConstants.REPLACE_IF_UNMODIFIED_RESPONSE;
         case HotRodConstants.REMOVE_REQUEST:
            return HotRodConstants.REMOVE_RESPONSE;
         case HotRodConstants.REMOVE_IF_UNMODIFIED_REQUEST:
            return HotRodConstants.REMOVE_IF_UNMODIFIED_RESPONSE;
         case HotRodConstants.CONTAINS_KEY_REQUEST:
            return HotRodConstants.CONTAINS_KEY_RESPONSE;
         case HotRodConstants.GET_WITH_VERSION:
            return HotRodConstants.GET_WITH_VERSION_RESPONSE;
         case HotRodConstants.CLEAR_REQUEST:
            return HotRodConstants.CLEAR_RESPONSE;
         case HotRodConstants.STATS_REQUEST:
            return HotRodConstants.STATS_RESPONSE;
         case HotRodConstants.PING_REQUEST:
            return HotRodConstants.PING_RESPONSE;
         case HotRodConstants.BULK_GET_REQUEST:
            return HotRodConstants.BULK_GET_RESPONSE;
         case HotRodConstants.GET_WITH_METADATA:
            return HotRodConstants.GET_WITH_METADATA_RESPONSE;
         case HotRodConstants.BULK_GET_KEYS_REQUEST:
            return HotRodConstants.BULK_GET_KEYS_RESPONSE;
         case HotRodConstants.QUERY_REQUEST:
            return HotRodConstants.QUERY_RESPONSE;
         case HotRodConstants.AUTH_MECH_LIST_REQUEST:
            return HotRodConstants.AUTH_MECH_LIST_RESPONSE;
         case HotRodConstants.AUTH_REQUEST:
            return HotRodConstants.AUTH_RESPONSE;
         case HotRodConstants.ADD_CLIENT_LISTENER_REQUEST:
            return HotRodConstants.ADD_CLIENT_LISTENER_RESPONSE;
         case HotRodConstants.REMOVE_CLIENT_LISTENER_REQUEST:
            return HotRodConstants.REMOVE_CLIENT_LISTENER_RESPONSE;
         case HotRodConstants.SIZE_REQUEST:
            return HotRodConstants.SIZE_RESPONSE;
         case HotRodConstants.EXEC_REQUEST:
            return HotRodConstants.EXEC_RESPONSE;
         case HotRodConstants.PUT_ALL_REQUEST:
            return HotRodConstants.PUT_ALL_RESPONSE;
         case HotRodConstants.GET_ALL_REQUEST:
            return HotRodConstants.GET_ALL_RESPONSE;
         case HotRodConstants.ITERATION_START_REQUEST:
            return HotRodConstants.ITERATION_START_RESPONSE;
         case HotRodConstants.ITERATION_NEXT_REQUEST:
            return HotRodConstants.ITERATION_NEXT_RESPONSE;
         case HotRodConstants.ITERATION_END_REQUEST:
            return HotRodConstants.ITERATION_END_RESPONSE;
         case HotRodConstants.GET_STREAM_REQUEST:
            return HotRodConstants.GET_STREAM_RESPONSE;
         case HotRodConstants.PUT_STREAM_REQUEST:
            return HotRodConstants.PUT_STREAM_RESPONSE;
         case HotRodConstants.COUNTER_CREATE_REQUEST:
            return HotRodConstants.COUNTER_CREATE_RESPONSE;
         case HotRodConstants.COUNTER_GET_CONFIGURATION_REQUEST:
            return HotRodConstants.COUNTER_GET_CONFIGURATION_RESPONSE;
         case HotRodConstants.COUNTER_IS_DEFINED_REQUEST:
            return HotRodConstants.COUNTER_IS_DEFINED_RESPONSE;
         case HotRodConstants.COUNTER_REMOVE_REQUEST:
            return HotRodConstants.COUNTER_REMOVE_RESPONSE;
         case HotRodConstants.COUNTER_ADD_AND_GET_REQUEST:
            return HotRodConstants.COUNTER_ADD_AND_GET_RESPONSE;
         case HotRodConstants.COUNTER_GET_REQUEST:
            return HotRodConstants.COUNTER_GET_RESPONSE;
         case HotRodConstants.COUNTER_RESET_REQUEST:
            return HotRodConstants.COUNTER_RESET_RESPONSE;
         case HotRodConstants.COUNTER_CAS_REQUEST:
            return HotRodConstants.COUNTER_CAS_RESPONSE;
         case HotRodConstants.COUNTER_GET_NAMES_REQUEST:
            return HotRodConstants.COUNTER_GET_NAMES_RESPONSE;
         case HotRodConstants.COUNTER_ADD_LISTENER_REQUEST:
            return HotRodConstants.COUNTER_ADD_LISTENER_RESPONSE;
         case HotRodConstants.COUNTER_REMOVE_LISTENER_REQUEST:
            return HotRodConstants.COUNTER_REMOVE_LISTENER_RESPONSE;
         default:
            throw new IllegalStateException("Unknown operation code: " + opCode);
      }
   }

   static int joinFlags(Flag[] flags) {
      int flagInt = 0;
      if (flags != null) {
         for (Flag flag : flags)
            flagInt = flag.getFlagInt() | flagInt;
      }
      return flagInt;
   }

}
