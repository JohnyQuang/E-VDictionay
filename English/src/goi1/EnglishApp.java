package goi1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class EnglishApp {
	private static Map<String, String> vocabulary =  new HashMap<>();
	private static Random random = new Random();
	private static List<String> usedWords = new ArrayList<>();
	
	public static void main(String[] args) {
		readVocabularyFromFile("/Users/nguyenquang/eclipse-workspace/English/able.txt");
		Scanner sc = new Scanner(System.in);
		System.out.println("Chương Trình Học Tiếng Anh Đơn Giản");
		
		int correctCout = 0;
		int totalWords = 30;
		int soTT=0;
		for (int i = 0; i < totalWords; i++) {
			//lấy một từ tiếng việt ngẫu nhiên
			soTT++;
			String vietnameseWord = getRandomWord();
			System.out.println(soTT+ ":" + "Nhập Tiếng Việt Của Từ: "+ vietnameseWord);
			
			//nhập câu trả lời của người chơi
			System.out.println("Nhập câu trả lời: ");
			String userAnswer= sc.nextLine();
			
			//ktr kết quả và hiện thị kq đúng
			String correctEnglishWord = vocabulary.get(vietnameseWord);
			if (userAnswer.equalsIgnoreCase(correctEnglishWord)) {
				System.out.println("Trả lời chính xác!\n -" +correctEnglishWord);
				correctCout++;
			}else {
				System.out.println("Sai rồi! Đáp án đúng là: "+correctEnglishWord);
			}
			System.out.println("số từ đúng: "+correctCout+"/"+totalWords);
			if (correctCout==30) {
				System.out.println("Chúc Mừng bạn đã hoàn thành 30 Câu trả lời đúng!\n Hoàn thành luyện tập");
				break;
			}
		}
	}
	//không lập lại từ
	private static String getRandomWord() {
	    List<String> remainingWords = new ArrayList<>(vocabulary.keySet());
	    remainingWords.removeIf(usedWords::contains);

	    if (remainingWords.isEmpty()) {
	        // Nếu đã sử dụng hết từ, đặt lại danh sách
	        usedWords.clear();
	        remainingWords.addAll(vocabulary.keySet());
	    }

	    String randomWord = remainingWords.get(random.nextInt(remainingWords.size()));
	    usedWords.add(randomWord);

	    return randomWord;
	}
	//đọc file dữ liệu
	private static void readVocabularyFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String vietnameseWord = parts[0].trim();
                    String englishWord = parts[1].trim();
                    vocabulary.put(vietnameseWord, englishWord);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
