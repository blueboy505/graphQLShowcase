package com.example.southpark.graphqlsouthparkdemo.graphql;

import graphql.language.IntValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeScalar extends GraphQLScalarType {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    private static final Coercing coercing = new Coercing() {
        @Override
        public Object serialize(Object input) {
            return sdf.format((Date) input);
        }

        @Override
        public Object parseValue(Object input) {
            if (input instanceof Long) {
                return new Date((Long)input);
            } else {
                return new Date(Long.parseLong(input.toString()));
            }
        }

        @Override
        public Object parseLiteral(Object input) {
            if (input.getClass().equals(IntValue.class)) {
                BigInteger value = ((IntValue) input).getValue();
                return new Date(value.longValue());
            } else if (input.getClass().equals(String.class)) {
                try {
                    return sdf.parse((String)input);
                } catch (ParseException e) {
                    throw new RuntimeException("Unexpected input, expected millis as long");
                }
            }
            throw new RuntimeException("Unexpected input, expected millis as long");
        }
    };

    public DateTimeScalar() {
        super("DateTime", "", coercing);
    }
}
