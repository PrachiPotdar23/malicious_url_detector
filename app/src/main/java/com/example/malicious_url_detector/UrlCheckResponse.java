package com.example.malicious_url_detector;

public class UrlCheckResponse {
    private String message;
    private boolean success;
    private boolean unsafe;
    private String domain;
    private String ip_address;
    private String country_code;
    private String language_code;
    private String server;
    private String content_type;
    private int status_code;
    private int page_size;
    private int domain_rank;
    private boolean dns_valid;
    private boolean parking;
    private boolean spamming;
    private boolean malware;
    private boolean phishing;
    private boolean suspicious;
    private boolean adult;
    private int risk_score;
    private DomainAge domain_age;
    private String category;
    private boolean redirected;
    private String request_id;

    // Getter and Setter methods for selected fields

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isUnsafe() {
        return unsafe;
    }

    public void setUnsafe(boolean unsafe) {
        this.unsafe = unsafe;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    // Add similar getter and setter methods for other fields

    public static class DomainAge {
        private String human;
        private long timestamp;
        private String iso;

        // Getter and Setter methods for the DomainAge fields

        public String getHuman() {
            return human;
        }

        public void setHuman(String human) {
            this.human = human;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getIso() {
            return iso;
        }

        public void setIso(String iso) {
            this.iso = iso;
        }
    }
}
