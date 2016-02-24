package com.segment.jsonrpc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class JsonRPCConverterFactory extends Converter.Factory {
    public static JsonRPCConverterFactory create() {
        return new JsonRPCConverterFactory();
    }

    private JsonRPCConverterFactory() {
        // Private constructor.
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (!Utils.isAnnotationPresent(annotations, JsonRPC.class)
                && !Utils.isAnnotationPresent(annotations, JsonRPC2.class)) {
            return null;
        }

        Type rpcType = Types.newParameterizedType(JsonRPCResponse.class, type);
        Converter<ResponseBody, JsonRPCResponse> delegate =
                retrofit.nextResponseBodyConverter(this, rpcType, annotations);
        //noinspection unchecked
        return new JsonRPCConverters.JsonRPCResponseBodyConverter(delegate);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {

        JsonRPC jsonRPCAnnotation = Utils.findAnnotation(methodAnnotations, JsonRPC.class);
        if (jsonRPCAnnotation != null) {
            String method = jsonRPCAnnotation.value();
            Converter<JsonRPCRequest, RequestBody> delegate =
                    retrofit.nextRequestBodyConverter(this, JsonRPCRequest.class, annotations,
                            methodAnnotations);
            //noinspection unchecked
            return new JsonRPCConverters.JsonRPCRequestBodyConverter(method, delegate);
        }

        JsonRPC2 jsonRPC2Annotation = Utils.findAnnotation(methodAnnotations, JsonRPC2.class);
        if (jsonRPC2Annotation != null) {
            String method = jsonRPC2Annotation.value();
            boolean namedParameters = jsonRPC2Annotation.namedParameters();
            boolean notification = jsonRPC2Annotation.notification();

            if (notification) {
                if (namedParameters) {
                    Converter<JsonRPC2NotificationNamedParameters, RequestBody> delegate =
                            retrofit.nextRequestBodyConverter(this, JsonRPC2NotificationNamedParameters.class, annotations,
                                    methodAnnotations);
                    //noinspection unchecked
                    return new JsonRPCConverters.JsonRPC2NotificationNamedParametersBodyConverter(method, delegate);
                } else {
                    Converter<JsonRPC2Notification, RequestBody> delegate =
                            retrofit.nextRequestBodyConverter(this, JsonRPC2Notification.class, annotations,
                                    methodAnnotations);
                    //noinspection unchecked
                    return new JsonRPCConverters.JsonRPC2NotificationBodyConverter(method, delegate);
                }
            } else {
                if (namedParameters) {
                    Converter<JsonRPC2RequestNamedParameters, RequestBody> delegate =
                            retrofit.nextRequestBodyConverter(this, JsonRPC2RequestNamedParameters.class, annotations,
                                    methodAnnotations);
                    //noinspection unchecked
                    return new JsonRPCConverters.JsonRPC2RequestNamedParametersBodyConverter(method, delegate);
                } else {
                    Converter<JsonRPC2Request, RequestBody> delegate =
                            retrofit.nextRequestBodyConverter(this, JsonRPC2Request.class, annotations,
                                    methodAnnotations);
                    //noinspection unchecked
                    return new JsonRPCConverters.JsonRPC2RequestBodyConverter(method, delegate);
                }
            }
        }
        return null;
    }

}
