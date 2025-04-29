package ch.example.springgraphqlkeycloakboilerplate.config;

import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

@Configuration
public class CustomScalarConfig {

    @Bean
    public GraphQLScalarType dateScalar() {
        return GraphQLScalarType.newScalar()
                .name("Date")
                .description("Java LocalDate as scalar.")
                .coercing(new Coercing<LocalDate, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) {
                        if (dataFetcherResult instanceof LocalDate) {
                            return ((LocalDate) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_DATE);
                        }
                        throw new CoercingSerializeException("Expected a LocalDate object.");
                    }

                    @Override
                    public LocalDate parseValue(Object input) {
                        try {
                            if (input instanceof String) {
                                return LocalDate.parse((String) input, DateTimeFormatter.ISO_LOCAL_DATE);
                            }
                            throw new CoercingParseValueException("Expected a String");
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input));
                        }
                    }

                    @Override
                    public LocalDate parseLiteral(Object input) {
                        if (input instanceof StringValue) {
                            try {
                                return LocalDate.parse(((StringValue) input).getValue(), DateTimeFormatter.ISO_LOCAL_DATE);
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        }
                        throw new CoercingParseLiteralException("Expected a StringValue.");
                    }
                }).build();
    }

    @Bean
    public GraphQLScalarType emailScalar() {
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

        return GraphQLScalarType.newScalar()
                .name("Email")
                .description("Email address scalar.")
                .coercing(new Coercing<String, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) {
                        if (dataFetcherResult instanceof String) {
                            String email = (String) dataFetcherResult;
                            if (emailPattern.matcher(email).matches()) {
                                return email;
                            }
                            throw new CoercingSerializeException("Invalid email format: " + email);
                        }
                        throw new CoercingSerializeException("Expected a String");
                    }

                    @Override
                    public String parseValue(Object input) {
                        if (input instanceof String) {
                            String email = (String) input;
                            if (emailPattern.matcher(email).matches()) {
                                return email;
                            }
                            throw new CoercingParseValueException("Invalid email format: " + email);
                        }
                        throw new CoercingParseValueException("Expected a String");
                    }

                    @Override
                    public String parseLiteral(Object input) {
                        if (input instanceof StringValue) {
                            String email = ((StringValue) input).getValue();
                            if (emailPattern.matcher(email).matches()) {
                                return email;
                            }
                            throw new CoercingParseLiteralException("Invalid email format: " + email);
                        }
                        throw new CoercingParseLiteralException("Expected a StringValue.");
                    }
                }).build();
    }

    @Bean
    public GraphQLScalarType registrationNumberScalar() {
        Pattern regNumberPattern = Pattern.compile("^[A-Z]{2}\\d{4}[A-Z]{2}$");

        return GraphQLScalarType.newScalar()
                .name("RegistrationNumber")
                .description("Car registration number scalar in format AA1234BB.")
                .coercing(new Coercing<String, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) {
                        if (dataFetcherResult instanceof String) {
                            String regNumber = (String) dataFetcherResult;
                            if (regNumberPattern.matcher(regNumber).matches()) {
                                return regNumber;
                            }
                            throw new CoercingSerializeException("Invalid registration number format: " + regNumber);
                        }
                        throw new CoercingSerializeException("Expected a String");
                    }

                    @Override
                    public String parseValue(Object input) {
                        if (input instanceof String) {
                            String regNumber = (String) input;
                            if (regNumberPattern.matcher(regNumber).matches()) {
                                return regNumber;
                            }
                            throw new CoercingParseValueException("Invalid registration number format: " + regNumber);
                        }
                        throw new CoercingParseValueException("Expected a String");
                    }

                    @Override
                    public String parseLiteral(Object input) {
                        if (input instanceof StringValue) {
                            String regNumber = ((StringValue) input).getValue();
                            if (regNumberPattern.matcher(regNumber).matches()) {
                                return regNumber;
                            }
                            throw new CoercingParseLiteralException("Invalid registration number format: " + regNumber);
                        }
                        throw new CoercingParseLiteralException("Expected a StringValue.");
                    }
                }).build();
    }
}