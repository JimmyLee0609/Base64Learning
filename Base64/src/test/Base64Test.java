package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {

		// Base64������encoder��ʹ�ã�decoder��ʹ��
		// encoder();
		urlEncoder();

		// �÷���һ�����ǿ����õ��ַ�����Щ��һ��
		Decoder decoder = Base64.getDecoder();
		Encoder encoder = Base64.getEncoder(); // Encoder.RFC4648;
		Decoder urlDecoder = Base64.getUrlDecoder();
		Encoder urlEncoder = Base64.getUrlEncoder(); // Encoder.RFC4648_URLSAFE;
		Decoder mimeDecoder = Base64.getMimeDecoder(); // Decoder.RFC2045
		Encoder mimeEncoder = Base64.getMimeEncoder();

		Base64.getMimeEncoder(5, new byte[] { 0x20, 0x0a });
	}

	@SuppressWarnings("unused")
	private static void urlEncoder() throws UnsupportedEncodingException {
		String url = new String("http://blog.csdn.net/chszs/article/details/17027649/���");
		Encoder urlEncoder = Base64.getUrlEncoder();

		byte[] encode = urlEncoder.encode(url.getBytes("utf-8"));
		String string = new String(encode);
		String string2 = new String(encode, "utf-8");

		Decoder urlDecoder = Base64.getUrlDecoder();
		byte[] decode = urlDecoder.decode(encode);
		String string3 = new String(decode);
		String string4 = new String(decode, "utf-8");

	}

	@SuppressWarnings("unused")
	private static void encoder() throws IOException {
		byte[] bytes = "test for base64 encode/r/n���Ի�".getBytes("utf-8");
		// ��ȡBase64������
		Encoder encoder = Base64.getEncoder();
		// ʹ�ý����������ݽ��ܲ�����String����
		byte[] encode2 = encoder.encode(bytes);
		// ��ȡ������
		Decoder decoder = Base64.getDecoder();
		// ����Ŀ���ַ�����
		byte[] decode = decoder.decode(encode2);
		// �����ܵ��������±������ԭ��������
		String string = new String(decode, "gbk");

		// ��ȡû�����ļ�����
		Encoder withoutPadding = encoder.withoutPadding();
		byte[] encode5 = encoder.encode(bytes);
		byte[] decode2 = decoder.decode(encode5);
		String string2 = new String(decode2, "utf-8");

		// ��װ������������ܵ�����д���ļ�
		OutputStream wrap = encoder.wrap(new FileOutputStream(new File("./doc/text.txt")));
		wrap.write(bytes);
		wrap.close();
		// ��װһ�������������ļ������ڴ棬���ܴ���õ�����
		InputStream wrap3 = decoder.wrap(new FileInputStream(new File("./doc/text.txt")));
		byte[] arr = new byte[bytes.length];
		int read = wrap3.read(arr);
		String string3 = new String(arr, "utf-8");

		// ��Դ�����װ��ByteBuffer,
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		// ʹ�ü���
		ByteBuffer encode = encoder.encode(buffer);
		// ʹ�ý���
		ByteBuffer decode3 = decoder.decode(encode);
		// �������������°�װ
		String string4 = new String(decode3.array(), "utf-8");

	}

}
