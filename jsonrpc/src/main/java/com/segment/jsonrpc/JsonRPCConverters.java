package com.segment.jsonrpc;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class JsonRPCConverters {
    static class JsonRPCResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        final Converter<ResponseBody, JsonRPCResponse<T>> delegate;

        JsonRPCResponseBodyConverter(Converter<ResponseBody, JsonRPCResponse<T>> delegate) {
            this.delegate = delegate;
        }

        @Override
        public T convert(ResponseBody responseBody) throws IOException {
            JsonRPCResponse<T> response = delegate.convert(responseBody);
            return response.result;
        }
    }

    static class JsonRPCRequestBodyConverter<T> implements Converter<T, RequestBody> {
        final String method;
        final Converter<JsonRPCRequest, RequestBody> delegate;

        JsonRPCRequestBodyConverter(String method, Converter<JsonRPCRequest, RequestBody> delegate) {
            this.method = method;
            this.delegate = delegate;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            return delegate.convert(JsonRPCRequest.create(method, value));
        }
    }

    static class JsonRPC2RequestBodyConverter<T> implements Converter<T, RequestBody> {
        final String method;
        final Converter<JsonRPC2Request, RequestBody> delegate;

        JsonRPC2RequestBodyConverter(String method, Converter<JsonRPC2Request, RequestBody> delegate) {
            this.method = method;
            this.delegate = delegate;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            return delegate.convert(JsonRPC2Request.create(method, value));
        }
    }

    static class JsonRPC2RequestNamedParametersBodyConverter<T> implements Converter<T, RequestBody> {
        final String method;
        final Converter<JsonRPC2RequestNamedParameters, RequestBody> delegate;

        JsonRPC2RequestNamedParametersBodyConverter(String method, Converter<JsonRPC2RequestNamedParameters, RequestBody> delegate) {
            this.method = method;
            this.delegate = delegate;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            return delegate.convert(JsonRPC2RequestNamedParameters.create(method, value));
        }
    }

    static class JsonRPC2NotificationBodyConverter<T> implements Converter<T, RequestBody> {
        final String method;
        final Converter<JsonRPC2Notification, RequestBody> delegate;

        JsonRPC2NotificationBodyConverter(String method, Converter<JsonRPC2Notification, RequestBody> delegate) {
            this.method = method;
            this.delegate = delegate;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            return delegate.convert(JsonRPC2Notification.create(method, value));
        }
    }

    static class JsonRPC2NotificationNamedParametersBodyConverter<T> implements Converter<T, RequestBody> {
        final String method;
        final Converter<JsonRPC2NotificationNamedParameters, RequestBody> delegate;

        JsonRPC2NotificationNamedParametersBodyConverter(String method, Converter<JsonRPC2NotificationNamedParameters, RequestBody> delegate) {
            this.method = method;
            this.delegate = delegate;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            return delegate.convert(JsonRPC2NotificationNamedParameters.create(method, value));
        }
    }
}
