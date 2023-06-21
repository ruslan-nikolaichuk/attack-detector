package io.httpflood.attackdetector.rules.impl;

public class BlockedIpDto {
    private String timestamp;
    private String ip;

    private BlockedIpDto(Builder builder) {
        this.timestamp = builder.timestamp;
        this.ip = builder.ip;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getIp() {
        return ip;
    }

    public static class Builder {
        private String timestamp;
        private String ip;

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public BlockedIpDto build() {
            return new BlockedIpDto(this);
        }
    }
}
