package own.hhw;// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Base64.java


public final class Base64
{

	private static final int BASELENGTH = 128;
	private static final int LOOKUPLENGTH = 64;
	private static final int TWENTYFOURBITGROUP = 24;
	private static final int EIGHTBIT = 8;
	private static final int SIXTEENBIT = 16;
	private static final int FOURBYTE = 4;
	private static final int SIGN = -128;
	private static final char PAD = 61;
	private static final boolean fDebug = false;
	private static final byte base64Alphabet[];
	private static final char lookUpBase64Alphabet[];

	public Base64()
	{
	}

	private static boolean isWhiteSpace(char octect)
	{
		return octect == ' ' || octect == '\r' || octect == '\n' || octect == '\t';
	}

	private static boolean isPad(char octect)
	{
		return octect == '=';
	}

	private static boolean isData(char octect)
	{
		return octect < '\200' && base64Alphabet[octect] != -1;
	}

	public static String encode(byte binaryData[])
	{
		if (binaryData == null)
			return null;
		int lengthDataBits = binaryData.length * 8;
		if (lengthDataBits == 0)
			return "";
		int fewerThan24bits = lengthDataBits % 24;
		int numberTriplets = lengthDataBits / 24;
		int numberQuartet = fewerThan24bits == 0 ? numberTriplets : numberTriplets + 1;
		char encodedData[] = null;
		encodedData = new char[numberQuartet * 4];
		byte k = 0;
		byte l = 0;
		byte b1 = 0;
		byte b2 = 0;
		byte b3 = 0;
		int encodedIndex = 0;
		int dataIndex = 0;
		for (int i = 0; i < numberTriplets; i++)
		{
			b1 = binaryData[dataIndex++];
			b2 = binaryData[dataIndex++];
			b3 = binaryData[dataIndex++];
			l = (byte)(b2 & 0xf);
			k = (byte)(b1 & 3);
			byte val1 = (b1 & 0xffffff80) != 0 ? (byte)(b1 >> 2 ^ 0xc0) : (byte)(b1 >> 2);
			byte val2 = (b2 & 0xffffff80) != 0 ? (byte)(b2 >> 4 ^ 0xf0) : (byte)(b2 >> 4);
			byte val3 = (b3 & 0xffffff80) != 0 ? (byte)(b3 >> 6 ^ 0xfc) : (byte)(b3 >> 6);
			encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
			encodedData[encodedIndex++] = lookUpBase64Alphabet[val2 | k << 4];
			encodedData[encodedIndex++] = lookUpBase64Alphabet[l << 2 | val3];
			encodedData[encodedIndex++] = lookUpBase64Alphabet[b3 & 0x3f];
		}

		if (fewerThan24bits == 8)
		{
			b1 = binaryData[dataIndex];
			k = (byte)(b1 & 3);
			byte val1 = (b1 & 0xffffff80) != 0 ? (byte)(b1 >> 2 ^ 0xc0) : (byte)(b1 >> 2);
			encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
			encodedData[encodedIndex++] = lookUpBase64Alphabet[k << 4];
			encodedData[encodedIndex++] = '=';
			encodedData[encodedIndex++] = '=';
		} else
		if (fewerThan24bits == 16)
		{
			b1 = binaryData[dataIndex];
			b2 = binaryData[dataIndex + 1];
			l = (byte)(b2 & 0xf);
			k = (byte)(b1 & 3);
			byte val1 = (b1 & 0xffffff80) != 0 ? (byte)(b1 >> 2 ^ 0xc0) : (byte)(b1 >> 2);
			byte val2 = (b2 & 0xffffff80) != 0 ? (byte)(b2 >> 4 ^ 0xf0) : (byte)(b2 >> 4);
			encodedData[encodedIndex++] = lookUpBase64Alphabet[val1];
			encodedData[encodedIndex++] = lookUpBase64Alphabet[val2 | k << 4];
			encodedData[encodedIndex++] = lookUpBase64Alphabet[l << 2];
			encodedData[encodedIndex++] = '=';
		}
		return new String(encodedData);
	}

	public static byte[] decode(String encoded)
	{
		if (encoded == null)
			return null;
		char base64Data[] = encoded.toCharArray();
		int len = removeWhiteSpace(base64Data);
		if (len % 4 != 0)
			return null;
		int numberQuadruple = len / 4;
		if (numberQuadruple == 0)
			return new byte[0];
		byte decodedData[] = null;
		byte b1 = 0;
		byte b2 = 0;
		byte b3 = 0;
		byte b4 = 0;
		char d1 = '\0';
		char d2 = '\0';
		char d3 = '\0';
		char d4 = '\0';
		int i = 0;
		int encodedIndex = 0;
		int dataIndex = 0;
		decodedData = new byte[numberQuadruple * 3];
		for (; i < numberQuadruple - 1; i++)
		{
			if (!isData(d1 = base64Data[dataIndex++]) || !isData(d2 = base64Data[dataIndex++]) || !isData(d3 = base64Data[dataIndex++]) || !isData(d4 = base64Data[dataIndex++]))
				return null;
			b1 = base64Alphabet[d1];
			b2 = base64Alphabet[d2];
			b3 = base64Alphabet[d3];
			b4 = base64Alphabet[d4];
			decodedData[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
			decodedData[encodedIndex++] = (byte)((b2 & 0xf) << 4 | b3 >> 2 & 0xf);
			decodedData[encodedIndex++] = (byte)(b3 << 6 | b4);
		}

		if (!isData(d1 = base64Data[dataIndex++]) || !isData(d2 = base64Data[dataIndex++]))
			return null;
		b1 = base64Alphabet[d1];
		b2 = base64Alphabet[d2];
		d3 = base64Data[dataIndex++];
		d4 = base64Data[dataIndex++];
		if (!isData(d3) || !isData(d4))
		{
			if (isPad(d3) && isPad(d4))
				if ((b2 & 0xf) != 0)
				{
					return null;
				} else
				{
					byte tmp[] = new byte[i * 3 + 1];
					System.arraycopy(decodedData, 0, tmp, 0, i * 3);
					tmp[encodedIndex] = (byte)(b1 << 2 | b2 >> 4);
					return tmp;
				}
			if (!isPad(d3) && isPad(d4))
			{
				b3 = base64Alphabet[d3];
				if ((b3 & 3) != 0)
				{
					return null;
				} else
				{
					byte tmp[] = new byte[i * 3 + 2];
					System.arraycopy(decodedData, 0, tmp, 0, i * 3);
					tmp[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
					tmp[encodedIndex] = (byte)((b2 & 0xf) << 4 | b3 >> 2 & 0xf);
					return tmp;
				}
			} else
			{
				return null;
			}
		} else
		{
			b3 = base64Alphabet[d3];
			b4 = base64Alphabet[d4];
			decodedData[encodedIndex++] = (byte)(b1 << 2 | b2 >> 4);
			decodedData[encodedIndex++] = (byte)((b2 & 0xf) << 4 | b3 >> 2 & 0xf);
			decodedData[encodedIndex++] = (byte)(b3 << 6 | b4);
			return decodedData;
		}
	}

	private static int removeWhiteSpace(char data[])
	{
		if (data == null)
			return 0;
		int newSize = 0;
		int len = data.length;
		for (int i = 0; i < len; i++)
			if (!isWhiteSpace(data[i]))
				data[newSize++] = data[i];

		return newSize;
	}

	static 
	{
		base64Alphabet = new byte[128];
		lookUpBase64Alphabet = new char[64];
		int i;
		for (i = 0; i < 128; i++)
			base64Alphabet[i] = -1;

		for (i = 90; i >= 65; i--)
			base64Alphabet[i] = (byte)(i - 65);

		for (i = 122; i >= 97; i--)
			base64Alphabet[i] = (byte)((i - 97) + 26);

		for (i = 57; i >= 48; i--)
			base64Alphabet[i] = (byte)((i - 48) + 52);

		base64Alphabet[43] = 62;
		base64Alphabet[47] = 63;
		for (i = 0; i <= 25; i++)
			lookUpBase64Alphabet[i] = (char)(65 + i);

		i = 26;
		for (int j = 0; i <= 51; j++)
		{
			lookUpBase64Alphabet[i] = (char)(97 + j);
			i++;
		}

		i = 52;
		for (int j = 0; i <= 61; j++)
		{
			lookUpBase64Alphabet[i] = (char)(48 + j);
			i++;
		}

		lookUpBase64Alphabet[62] = '+';
		lookUpBase64Alphabet[63] = '/';
	}
}
