package com.anthony.library.system.handler;

import java.util.List;

public class ErrorResponse {

    private String message;
    private String code;
    private List<ValidationError> validationErrors;

    protected ErrorResponse() {
    }

    private ErrorResponse(builder builder) {
        this.message = builder.message;
        this.code = builder.code;
        this.validationErrors = builder.validationErrors;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", validationErrors=" + validationErrors +
                '}';
    }

    public static class builder {

        String message;
        String code;
        List<ValidationError> validationErrors;

        public builder setMessage(String message) {

            this.message = message;
            return this;
        }

        public builder setCode(String code) {

            this.code = code;
            return this;
        }

        public builder setValidationErrors(List<ValidationError> validationErrors) {

            this.validationErrors = validationErrors;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }

    public static class ValidationError {
        private String field;
        private String code;
        private String message;

        protected ValidationError() {
        }

        private ValidationError(builder builder) {

            this.field = builder.field;
            this.code = builder.code;
            this.message = builder.message;
        }

        public String getField() {
            return field;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "ValidationError{" +
                    "field='" + field + '\'' +
                    ", code='" + code + '\'' +
                    ", message=" + message +
                    '}';
        }

        public static class builder {

            String field;
            String code;
            String message;

            public builder setField(String field) {

                this.field = field;
                return this;
            }

            public builder setCode(String code) {

                this.code = code;
                return this;
            }

            public builder setMessage(String message) {

                this.message = message;
                return this;
            }

            public ValidationError build() {

                return new ValidationError(this);
            }
        }
    }

}
