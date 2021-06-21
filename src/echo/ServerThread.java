package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	// 필드
	private Socket socket;

	// 생성자
	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// 메소드(게터세터)

	// 메소드(일반)
	// 출장관련 (독립적인 프로세서) 부모쪽에 구현되어있다.

	// 출장가서 해야할 클래스 -- 메세지 주고받기!

	@Override
	public void run() {
		// 메세지 주고받기
		// 메세지 받기용 스트림

		try {
			InputStream is = socket.getInputStream(); // 이미 소켓이 만들어놓음
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			// 메세지 보내기용 스트림
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			while (true) {

				// 메세지받기
				String msg = br.readLine();

				if (msg == null) { // null이라는건 글자가아니고 주소를 비교하기때문에 ==

					System.out.println("클라이언트 접속 종료");
					break;
				}

				System.out.println("받은메세지: " + msg);
				// 메세지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush(); // 용량이 안차도 보내줘라!

			}

		} catch (IOException e) {
			System.out.println("에러발생");
		}

	}

}
