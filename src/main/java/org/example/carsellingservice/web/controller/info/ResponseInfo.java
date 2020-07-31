package org.example.carsellingservice.web.controller.info;

public class ResponseInfo {
    private boolean successful;

    public ResponseInfo(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
