package listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import utility.ExcelUtils;

public class MethodIntercepter implements IMethodInterceptor {

	@Override
	public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {

		List<Map<String, String>> testDetails = ExcelUtils.getTestDetails();

		List<IMethodInstance> results = new ArrayList<IMethodInstance>();

		for(int i=0;i<methods.size();i++) {
			for(int j=0;j<testDetails.size();j++) {
                
				if(	methods.get(i).getMethod().getMethodName().equals(testDetails.get(j).get("testname"))) {
					if(testDetails.get(j).get("execute").equals("yes")) {
						methods.get(i).getMethod().setDescription(testDetails.get(j).get("description"));
						results.add(methods.get(i));
					}

				}
			}
		}
		
		return results;
	}

}
