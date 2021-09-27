package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static List<Map<String, String>> getTestDetails() {

		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		FileInputStream fs = null;
		try {
			 fs = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/excel/testcase.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet("run_manager");
			int totalRow = sheet.getLastRowNum();
			int totalCell = sheet.getRow(0).getLastCellNum();
			for(int i = 1;i<=totalRow;i++) {
				Map<String, String> map = new HashMap<String, String>();
				for(int j=0;j<totalCell;j++) {
					String key = sheet.getRow(0).getCell(j).getStringCellValue();
					String value = sheet.getRow(i).getCell(j).getStringCellValue();
					map.put(key, value);  
					
				}
				result.add(map);
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(Objects.nonNull(fs)) {
				
				try {
					fs.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return result;


	}

}
