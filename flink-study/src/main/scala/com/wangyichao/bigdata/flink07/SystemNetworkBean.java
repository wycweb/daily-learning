package com.wangyichao.bigdata.flink07;

public class SystemNetworkBean {
    private String event_time;
    private long system_network_out_bytes;
    private int system_network_out_errors;
    private long system_network_out_packets;
    private long system_network_out_dropped;
    private String system_network_name;
    private long system_network_in_bytes;
    private int system_network_in_errors;
    private long system_network_in_packets;
    private long system_network_in_dropped;
    private String host_name;

    public SystemNetworkBean(String event_time, long system_network_out_bytes, int system_network_out_errors, long system_network_out_packets, long system_network_out_dropped, String system_network_name, long system_network_in_bytes, int system_network_in_errors, long system_network_in_packets, long system_network_in_dropped, String host_name) {
        this.event_time = event_time;
        this.system_network_out_bytes = system_network_out_bytes;
        this.system_network_out_errors = system_network_out_errors;
        this.system_network_out_packets = system_network_out_packets;
        this.system_network_out_dropped = system_network_out_dropped;
        this.system_network_name = system_network_name;
        this.system_network_in_bytes = system_network_in_bytes;
        this.system_network_in_errors = system_network_in_errors;
        this.system_network_in_packets = system_network_in_packets;
        this.system_network_in_dropped = system_network_in_dropped;
        this.host_name = host_name;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public long getSystem_network_out_bytes() {
        return system_network_out_bytes;
    }

    public void setSystem_network_out_bytes(long system_network_out_bytes) {
        this.system_network_out_bytes = system_network_out_bytes;
    }

    public int getSystem_network_out_errors() {
        return system_network_out_errors;
    }

    public void setSystem_network_out_errors(int system_network_out_errors) {
        this.system_network_out_errors = system_network_out_errors;
    }

    public long getSystem_network_out_packets() {
        return system_network_out_packets;
    }

    public void setSystem_network_out_packets(long system_network_out_packets) {
        this.system_network_out_packets = system_network_out_packets;
    }

    public long getSystem_network_out_dropped() {
        return system_network_out_dropped;
    }

    public void setSystem_network_out_dropped(long system_network_out_dropped) {
        this.system_network_out_dropped = system_network_out_dropped;
    }

    public String getSystem_network_name() {
        return system_network_name;
    }

    public void setSystem_network_name(String system_network_name) {
        this.system_network_name = system_network_name;
    }

    public long getSystem_network_in_bytes() {
        return system_network_in_bytes;
    }

    public void setSystem_network_in_bytes(long system_network_in_bytes) {
        this.system_network_in_bytes = system_network_in_bytes;
    }

    public int getSystem_network_in_errors() {
        return system_network_in_errors;
    }

    public void setSystem_network_in_errors(int system_network_in_errors) {
        this.system_network_in_errors = system_network_in_errors;
    }

    public long getSystem_network_in_packets() {
        return system_network_in_packets;
    }

    public void setSystem_network_in_packets(long system_network_in_packets) {
        this.system_network_in_packets = system_network_in_packets;
    }

    public long getSystem_network_in_dropped() {
        return system_network_in_dropped;
    }

    public void setSystem_network_in_dropped(long system_network_in_dropped) {
        this.system_network_in_dropped = system_network_in_dropped;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    @Override
    public String toString() {
        return "SystemNetworkBean{" +
                "event_time='" + event_time + '\'' +
                ", system_network_out_bytes=" + system_network_out_bytes +
                ", system_network_out_errors=" + system_network_out_errors +
                ", system_network_out_packets=" + system_network_out_packets +
                ", system_network_out_dropped=" + system_network_out_dropped +
                ", system_network_name='" + system_network_name + '\'' +
                ", system_network_in_bytes=" + system_network_in_bytes +
                ", system_network_in_errors=" + system_network_in_errors +
                ", system_network_in_packets=" + system_network_in_packets +
                ", system_network_in_dropped=" + system_network_in_dropped +
                ", host_name='" + host_name + '\'' +
                '}';
    }
}
