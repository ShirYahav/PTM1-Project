package test;

import java.util.Date;

public final class Message {
    public final byte[] data;
    public final String asText;
    public final double asDouble;
    public final Date date;

    public Message(final String asText) {
        this.asText = asText;
        this.data = asText.getBytes();
        double tempDouble;
        try{
            tempDouble = Double.parseDouble(asText);
        }catch(NumberFormatException e){
            tempDouble = Double.NaN;
        }
        this.asDouble = tempDouble;
        this.date = new Date();
    }

    public Message(final double asDouble) {
        this(String.valueOf(asDouble));
    }

    public Message(final byte[] data) {
        this(new String(data));
    }
}
