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

		// Base64加密器encoder的使用，decoder的使用
		// encoder();
		urlEncoder();

		// 用法都一样就是可以用的字符集有些不一样
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
		String url = new String("http://blog.csdn.net/chszs/article/details/17027649/最好");
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
		byte[] bytes = "test for base64 encode/r/n测试机".getBytes("utf-8");
		// 获取Base64加密器
		Encoder encoder = Base64.getEncoder();
		// 使用解密器将数据解密并返回String类型
		byte[] encode2 = encoder.encode(bytes);
		// 获取解密器
		Decoder decoder = Base64.getDecoder();
		// 解密目标字符串，
		byte[] decode = decoder.decode(encode2);
		// 将解密的内容重新编码组成原来的内容
		String string = new String(decode, "gbk");

		// 获取没有填充的加密器
		Encoder withoutPadding = encoder.withoutPadding();
		byte[] encode5 = encoder.encode(bytes);
		byte[] decode2 = decoder.decode(encode5);
		String string2 = new String(decode2, "utf-8");

		// 包装输出流，将加密的内容写到文件
		OutputStream wrap = encoder.wrap(new FileOutputStream(new File("./doc/text.txt")));
		wrap.write(bytes);
		wrap.close();
		// 包装一个输入流并将文件读到内存，解密处理得到内容
		InputStream wrap3 = decoder.wrap(new FileInputStream(new File("./doc/text.txt")));
		byte[] arr = new byte[bytes.length];
		int read = wrap3.read(arr);
		String string3 = new String(arr, "utf-8");

		// 将源数组包装成ByteBuffer,
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		// 使用加密
		ByteBuffer encode = encoder.encode(buffer);
		// 使用解密
		ByteBuffer decode3 = decoder.decode(encode);
		// 将解密数据重新包装
		String string4 = new String(decode3.array(), "utf-8");

	}

}
