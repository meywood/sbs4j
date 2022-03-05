package dev.oak3.sbs4j.model;

import dev.oak3.sbs4j.SerializerBuffer;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A container for test data for the Serializer/Deserializer
 *
 * @since 0.1.0
 */
public class TestData<T> {
    /*
     * Type name constants
     */
    public static final String BOOLEAN = "BOOLEAN";
    public static final String U_8 = "U8";
    public static final String BYTE_ARRAY = "BYTE_ARRAY";
    public static final String U_32 = "U32";
    public static final String I_32 = "I32";
    public static final String F_32 = "F32";
    public static final String F_64 = "F64";
    public static final String I_64 = "I64";
    public static final String U_64 = "U64";
    public static final String U_128 = "U128";
    public static final String U_256 = "U256";
    public static final String U_512 = "U512";
    public static final String STRING = "STRING";

    private final String name;
    private final T value;
    private final byte[] byteValue;

    /**
     * Gets the test data name
     *
     * @return the test data name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the test data value
     *
     * @return the test data value
     */
    public T getValue() {
        return value;
    }

    /**
     * Gets the test data byte value
     *
     * @return the test data byte value
     */
    public byte[] getByteValue() {
        return byteValue;
    }

    /**
     * Data with correct value/byteValue for loop testing serializer/deserializer
     */
    public final static List<TestData<?>> SUCCESS_TEST_DATA = Arrays.asList(
            new TestData<>(BOOLEAN, true, new byte[]{1}),
            new TestData<>(U_8, Byte.valueOf("7"), new byte[]{7}),
            new TestData<>(BYTE_ARRAY, new byte[]{'s', 'b', 's', '4', 'j'}, new byte[]{115, 98, 115, 52, 106}),
            new TestData<>(U_32, 7L, new byte[]{7, 0, 0, 0, 0, 0, 0, 0}),
            new TestData<>(I_32, 7, new byte[]{7, 0, 0, 0}),
            new TestData<>(F_32, (float) 5, new byte[]{0, 0, -96, 64}),
            new TestData<>(F_64, (double) 5, new byte[]{0, 0, 0, 0, 0, 0, 20, 64}),
            new TestData<>(I_64, 7L, new byte[]{7, 0, 0, 0, 0, 0, 0, 0}),
            new TestData<>(U_64, new BigInteger("1024", 10), new byte[]{0, 4, 0, 0, 0, 0, 0, 0}),
            new TestData<>(U_64, new BigInteger("18446744073709551615", 10), new byte[]{-1, -1, -1, -1, -1, -1, -1, -1}),
            new TestData<>(U_512, new BigInteger("7", 10), new byte[]{1, 7}),
            new TestData<>(U_512, new BigInteger("1024", 10), new byte[]{2, 4, 0}),
            new TestData<>(U_512, new BigInteger("123456789101112131415", 10), new byte[]{9, 6, -79, 78, -97, -107, -38, 26, -1, 87}),
            new TestData<>(U_512, new BigInteger("2500010000", 10), new byte[]{4, -107, 3, 32, 16}),
            new TestData<>(STRING, "the string", new byte[]{10, 0, 0, 0, 116, 104, 101, 32, 115, 116, 114, 105, 110, 103}),
            new TestData<>(STRING, "Hello, World!", new byte[]{13, 0, 0, 0, 72, 101, 108, 108, 111, 44, 32, 87, 111, 114, 108, 100, 33}));


    /**
     * Test data for ensuring numeric values would serialize until inside its valid bounds
     */
    public final static List<TestData<?>> LAST_VALID_NUMBER_TEST_DATA = Arrays.asList(
                    new TestData<>(U_64, SerializerBuffer.MAX_U64, null),
                    new TestData<>(U_128, SerializerBuffer.MAX_U128, null),
                    new TestData<>(U_256, SerializerBuffer.MAX_U256, null),
                    new TestData<>(U_512, SerializerBuffer.MAX_U512, null));

    /**
     * Test data for ensuring numeric values would throw while serializing outside its valid bounds
     */
    public final static List<TestData<?>> OUT_OF_BOUNDS_TEST_DATA = Arrays.asList(
                    new TestData<>(U_64, SerializerBuffer.MAX_U64.add(SerializerBuffer.ONE), null),
                    new TestData<>(U_128, SerializerBuffer.MAX_U128.add(SerializerBuffer.ONE), null),
                    new TestData<>(U_256, SerializerBuffer.MAX_U256.add(SerializerBuffer.ONE), null),
                    new TestData<>(U_512, SerializerBuffer.MAX_U512.add(SerializerBuffer.ONE), null));

    /**
     * Instantiates a TestData object
     *
     * @param name      its identifier name
     * @param value     its value
     * @param byteValue its corresponding value in bytes
     */
    public TestData(String name, T value, byte[] byteValue) {
        this.name = name;
        this.value = value;
        this.byteValue = byteValue;
    }
}
