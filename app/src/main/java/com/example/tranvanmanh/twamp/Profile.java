package com.example.tranvanmanh.twamp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tranvanmanh on 4/4/2018.
 */

public class Profile implements Serializable{
    private String DestinationIP;
    private Integer Duplicatepackets;
    private IPDV IPDV;
    private Integer Interdelayus;
    private Latency Latency;
    private Integer Lostpackets;
    private Integer Outoforderpacket;
    private List<Integer> PDV = null;
    private Integer Payloadbyte;
    private List<Long> Receivetime = null;
    private List<Long> Reflectpacket = null;
    private List<Long> SendPacket = null;
    private List<Long> Sendtime = null;
    private String SourceIP;
    private Integer TotalPacket;

    public String getDestinationIP() {
        return DestinationIP;
    }

    public void setDestinationIP(String destinationIP) {
        this.DestinationIP = DestinationIP;
    }

    public Integer getDuplicatepackets() {
        return Duplicatepackets;
    }

    public void setDuplicatepackets(Integer Duplicatepackets) {
        this.Duplicatepackets = Duplicatepackets;
    }

    public IPDV getIPDV() {
        return IPDV;
    }

    public void setIPDV(IPDV IPDV) {
        this.IPDV = IPDV;
    }

    public Integer getInterdelayus() {
        return Interdelayus;
    }

    public void setInterdelayus(Integer Interdelayus) {
        this.Interdelayus = Interdelayus;
    }

    public Latency getLatency() {
        return Latency;
    }

    public void setLatency(Latency Latency) {
        this.Latency = Latency;
    }

    public Integer getLostpackets() {
        return Lostpackets;
    }

    public void setLostpackets(Integer Lostpackets) {
        this.Lostpackets = Lostpackets;
    }

    public Integer getOutoforderpacket() {
        return Outoforderpacket;
    }

    public void setOutoforderpacket(Integer Outoforderpacket) {
        this.Outoforderpacket = Outoforderpacket;
    }

    public List<Integer> getPDV() {
        return PDV;
    }

    public void setPDV(List<Integer> PDV) {
        this.PDV = PDV;
    }

    public Integer getPayloadbyte() {
        return Payloadbyte;
    }

    public void setPayloadbyte(Integer Payloadbyte) {
        this.Payloadbyte = Payloadbyte;
    }

    public List<Long> getReceivetime() {
        return Receivetime;
    }

    public void setReceivetime(List<Long> Receivetime) {
        this.Receivetime = Receivetime;
    }

    public List<Long> getReflectpacket() {
        return Reflectpacket;
    }

    public void setReflectpacket(List<Long> Reflectpacket) {
        this.Reflectpacket = Reflectpacket;
    }

    public List<Long> getSendPacket() {
        return SendPacket;
    }

    public void setSendPacket(List<Long> SendPacket) {
        this.SendPacket = SendPacket;
    }

    public List<Long> getSendtime() {
        return Sendtime;
    }

    public void setSendtime(List<Long> Sendtime) {
        this.Sendtime = Sendtime;
    }

    public String getSourceIP() {
        return SourceIP;
    }

    public void setSourceIP(String SourceIP) {
        this.SourceIP = SourceIP;
    }

    public Integer getTotalPacket() {
        return TotalPacket;
    }

    public void setTotalPacket(Integer TotalPacket) {
        this.TotalPacket = TotalPacket;
    }
}
