package com.jonathandiz.convertidor_de_monedas.utils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class CustomCurrency extends Number implements Comparable<CustomCurrency>, Serializable {

    @Serial
    private static final long serialVersionUID = -4557070039588615025L;

    private static final Set<String> ISO_4217_CURRENCY_CODES = Set.of(
        "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN",
        "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BOV", 
        "BRL", "BSD", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHE", "CHF", 
        "CHW", "CLF", "CLP", "CNY", "COP", "COU", "CRC", "CUC", "CUP", "CVE", 
        "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", 
        "FKP", "FOK", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", 
        "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", 
        "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", 
        "KID", "KMF", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", 
        "LSL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRU", 
        "MUR", "MVR", "MWK", "MXN", "MXV", "MYR", "MZN", "NAD", "NGN", "NIO", 
        "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", 
        "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", 
        "SEK", "SGD", "SHP", "SLE", "SLL", "SOS", "SRD", "SSP", "STN", "SYP", 
        "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TVD", "TWD", 
        "TZS", "UAH", "UGX", "USD", "USN", "UYI", "UYU", "UYW", "UZS", "VED", 
        "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XBA", "XBB", "XBC", "XBD", 
        "XCD", "XDR", "XOF", "XPD", "XPF", "XPT", "XSU", "XTS", "XUA", "XXX", 
        "YER", "ZAR", "ZMW", "ZWL"
    );

    private final BigDecimal value;
    private final String currencyCode;

    public CustomCurrency(BigDecimal value, String currencyCode) {
        if (value == null) {
            throw new IllegalArgumentException("El valor no puede ser nulo.");
        }
        if (!esCodigoDeMonedaValido(currencyCode)) {
            throw new IllegalArgumentException("El código de moneda '" + currencyCode + "' no es válido. Debe cumplir con el formato ISO 4217.");
        }
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public CustomCurrency(double value, String currencyCode) {
        this(BigDecimal.valueOf(value), currencyCode);
    }

    private static boolean esCodigoDeMonedaValido(String currencyCode) {
        if (currencyCode == null || currencyCode.length() != 3) {
            return false;
        }
        return ISO_4217_CURRENCY_CODES.contains(currencyCode.toUpperCase());
    }

    public CustomCurrency add(CustomCurrency other) {
        validarMonedaCompatible(other);
        return new CustomCurrency(this.value.add(other.value), this.currencyCode);
    }

    public CustomCurrency subtract(CustomCurrency other) {
        validarMonedaCompatible(other);
        return new CustomCurrency(this.value.subtract(other.value), this.currencyCode);
    }

    public CustomCurrency multiply(BigDecimal multiplicador) {
        return new CustomCurrency(this.value.multiply(multiplicador), this.currencyCode);
    }

    public CustomCurrency divide(BigDecimal divisor) {
        return new CustomCurrency(this.value.divide(divisor), this.currencyCode);
    }

    @Override
    public int intValue() {
        return value.intValue();
    }

    @Override
    public long longValue() {
        return value.longValue();
    }

    @Override
    public float floatValue() {
        return value.floatValue();
    }

    @Override
    public double doubleValue() {
        return value.doubleValue();
    }

    private void validarMonedaCompatible(CustomCurrency other) {
        if (!this.currencyCode.equals(other.currencyCode)) {
            throw new IllegalArgumentException("Las monedas deben ser iguales para realizar operaciones.");
        }
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public String toString() {
        return value + " " + currencyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomCurrency that = (CustomCurrency) o;
        return value.equals(that.value) && currencyCode.equals(that.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currencyCode);
    }

    @Override
    public int compareTo(CustomCurrency other) {
        validarMonedaCompatible(other);
        return this.value.compareTo(other.value);
    }
}
