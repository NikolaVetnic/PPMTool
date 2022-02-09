package com.nv.ppmtool.payload;

public class JWTLoginSuccessResponse {

    private boolean success;
    private String token;

    public JWTLoginSuccessResponse(boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    public boolean isSuccess()  { return success;   }
    public String getToken()    { return token;     }

    public void setSuccess(boolean success) { this.success = success;   }
    public void setToken(String token)      { this.token = token;       }

    @Override
    public String toString() {
        return "JWTLoginSuccessResponse{" +
                "success=" + success +
                ", token='" + token + '\'' +
                '}';
    }
}
