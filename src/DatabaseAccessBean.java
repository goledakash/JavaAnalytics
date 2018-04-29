
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.math3.stat.StatUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.TimeSeries;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;



@ManagedBean(name ="databaseAccessBean")
@SessionScoped
public class DatabaseAccessBean {
	

	
	
	
	List<String> tableList;
	List<String> columnList;
	List<String> selectedColumns;
	List<List<Object>> output_table;
	List<String> columnListLabel;
	List<String> outputColumnList;
	
	



	String selectedTable;
	String selected_schema = "connect";
	DatabaseConnectionBean databaseConnectionBean;
	String errorMessage;
	String query;
	String query1;
	String selected_column;
	String selected_order;
	List<String> selectedColumnList;
	List<String> selectedOrderList;
	String sort_string;
	
	boolean errorFlag;
	boolean columnListRendered;
	boolean displayTable;
	

	double minValue;
	double maxValue;
	double mean ;
	double variance;
	double std ;
	double median;
	double q1;
	double q3 ;
	
	String graph_name;
	Boolean graph_flag;
	List<String> selectedPredictorColumns;
	String selectedResponseColumn;
	int value;
	int rowCount;
	int colCount;
	

	
	



	public int getColCount() {
		return colCount;
	}



	public void setColCount(int colCount) {
		this.colCount = colCount;
	}



	public int getRowCount() {
		return rowCount;
	}



	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}



	public int getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}



	public List<String> getSelectedPredictorColumns() {
		return selectedPredictorColumns;
	}



	public void setSelectedPredictorColumns(List<String> selectedPredictorColumns) {
		this.selectedPredictorColumns = selectedPredictorColumns;
	}



	public String getSelectedResponseColumn() {
		return selectedResponseColumn;
	}



	public void setSelectedResponseColumn(String selectedResponseColumn) {
		this.selectedResponseColumn = selectedResponseColumn;
	}



	public String getGraph_name() {
		return graph_name;
	}



	public void setGraph_name(String graph_name) {
		this.graph_name = graph_name;
	}



	public Boolean getGraph_flag() {
		return graph_flag;
	}



	public void setGraph_flag(Boolean graph_flag) {
		this.graph_flag = graph_flag;
	}



	public String getQuery1() {
		return query1;
	}



	public void setQuery1(String query1) {
		this.query1 = query1;
	}



	public double getMinValue() {
		return minValue;
	}



	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}



	public double getMaxValue() {
		return maxValue;
	}



	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}



	public double getMean() {
		return mean;
	}



	public void setMean(double mean) {
		this.mean = mean;
	}



	public double getVariance() {
		return variance;
	}



	public void setVariance(double variance) {
		this.variance = variance;
	}



	public double getStd() {
		return std;
	}



	public void setStd(double std) {
		this.std = std;
	}



	public double getMedian() {
		return median;
	}



	public void setMedian(double median) {
		this.median = median;
	}



	public double getQ1() {
		return q1;
	}



	public void setQ1(double q1) {
		this.q1 = q1;
	}



	public double getQ3() {
		return q3;
	}



	public void setQ3(double q3) {
		this.q3 = q3;
	}



	public String getSort_string() {
		return sort_string;
	}

	

	public void setSort_string(String sort_string) {
		this.sort_string = sort_string;
	
	}


	
	public String getSelected_column() {
		return selected_column;
	}


	public void setSelected_column(String selected_column) {
	
		this.selected_column = selected_column;
	}


	
	public String getSelected_order() {
		return selected_order;
	}


	
	public void setSelected_order(String selected_order) {
		this.selected_order = selected_order;
	
	}


	
	public String getSelected_schema() {
		return selected_schema;
	}

	
	
	

	public void setSelected_schema(String selected_schema) {
		this.selected_schema = selected_schema;
	}


	
	
	
	public List<String> getColumnListLabel() {
		return columnListLabel;
	}


	
	public void setColumnListLabel(List<String> columnListLabel) {
		this.columnListLabel = columnListLabel;
	}


	
	public List<String> getOutputColumnList() {
		return outputColumnList;
	}


	public void setOutputColumnList(List<String> outputColumnList) {
	
		this.outputColumnList = outputColumnList;
	}

	

	public List<String> getTableList() {
		return tableList;
	}


	
	public void setTableList(List<String> tableList) {
		this.tableList = tableList;
	}


	
	public List<String> getColumnList() {
		return columnList;
	}


	
	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	
	}


	
	public List<String> getSelectedColumns() {
		return selectedColumns;
	}


	
	public void setSelectedColumns(List<String> selectedColumns) {
		this.selectedColumns = selectedColumns;
	}

	




	public String getSelectedTable() {
		return selectedTable;
	}


	
	public void setSelectedTable(String selectedTable) {
		this.selectedTable = selectedTable;
	}


	
	public DatabaseConnectionBean getDatabaseConnectionBean() {
		return databaseConnectionBean;
	}


	
	public void setDatabaseConnectionBean(DatabaseConnectionBean databaseConnectionBean) {
		this.databaseConnectionBean = databaseConnectionBean;
	}


	
	public String getErrorMessage() {
		return errorMessage;
	}


	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	
	public boolean isErrorFlag() {
		return errorFlag;
	}

	

	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	
	}


	public boolean isColumnListRendered() {
	
		return columnListRendered;
	}


	public void setColumnListRendered(boolean columnListRendered) {
		this.columnListRendered = columnListRendered;
	}

	

	public boolean isDisplayTable() {
		return displayTable;
	}


	
	public void setDisplayTable(boolean displayTable) {
		this.displayTable = displayTable;
	}


	
	public String getQuery() {
		return query;
	}


	
	public void setQuery(String query) {
		this.query = query;
	}

	
	
	
	public List<List<Object>> getOutput_table() {
		return output_table;
	}


	
	public void setOutput_table(List<List<Object>> output_table) {
		this.output_table = output_table;
	}
	
	
	
	
	
	@PostConstruct
	public void init() 
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Map <String, Object> m = context.getExternalContext().getSessionMap();
		databaseConnectionBean = (DatabaseConnectionBean) m.get("databaseConnectionBean");
		selectedColumns = new ArrayList<String>();
		output_table = new ArrayList<List<Object>>();
		columnListRendered = false;
		errorFlag = false;
		displayTable = false;
		selectedTable="";
		errorMessage="";
		query="";
		DisplayTableList();
	}
	
	public void testf(AjaxBehaviorEvent abe){
		DisplayTableList();
	}


		
		
	
	
	
	public String DisplayTableList(){
		
		System.out.println(76256);
		try {
		String query1 = "";	
		
			query1 = "select table_name from information_schema.tables where table_schema='"+selected_schema+"';";
			
		
		ResultSet rs = databaseConnectionBean.readFromDatabase(query1);
		tableList = new ArrayList<String>();
		while(rs.next()){
				tableList.add(rs.getString(1));
				
		
		}
		
		
		} catch (SQLException e) {
			errorMessage = e.getMessage();
			e.printStackTrace();
			errorFlag=true;
		}
		
		
		
		columnList = new ArrayList<String>();
		
		return "SUCCESS";
	}
	
	
	
	
	public String DisplayColumnList(){
		
		try {
			
		System.out.println(selectedTable);	
		String query1 = "select * from "+selected_schema+"."+selectedTable;
		ResultSet rs = databaseConnectionBean.readFromDatabase(query1);
		columnList = new ArrayList<String>();
		selectedColumnList = new ArrayList<String>();
		selectedOrderList = new ArrayList<String>();

		//columnListLabel = new ArrayList<String>();
		
		ResultSetMetaData result_meta = rs.getMetaData();
		System.out.println(result_meta.getColumnCount());	

		for (int i = 1; i < result_meta.getColumnCount()+1; i++) {
			//columnList.add(result_meta.getColumnName(i));
			columnList.add(result_meta.getColumnName(i)+" : "+result_meta.getColumnTypeName(i));
			
			//System.out.println(result_meta.getColumnName(i));	

		}
		
		} 
		
		catch (SQLException e) {
			errorMessage = e.getMessage();
			errorFlag=true;
		}
		
		
		columnListRendered=true;
		return "SUCCESS";

		
		
	}
	
	
	
	
public void setOutColList(ResultSetMetaData result_meta){
		
		try {
		
		outputColumnList = new ArrayList<String>();

		for (int i = 1; i < result_meta.getColumnCount()+1; i++) {
			outputColumnList.add(result_meta.getColumnName(i));
			System.out.println(result_meta.getColumnName(i));	

		}
		
		
		} 
		catch (SQLException e) {
			errorMessage = e.getMessage();
			errorFlag=true;
		}
	
		
	}


	
	public String DisplayTableName(){
		
		try {
			
			output_table = new ArrayList<List<Object>>();

			query = "select * from "+selected_schema+"."+selectedTable;
			ResultSet rs = databaseConnectionBean.readFromDatabase(query);
			ResultSetMetaData result_meta = rs.getMetaData();
			setOutColList(result_meta);
			
			int columnCount = result_meta.getColumnCount();

			while (rs.next()) {
			  
			    List<Object> columns = new ArrayList<Object>();

			    for (int i = 1; i <= columnCount; i++) {
			        columns.add(rs.getObject(i));
			        
			    }

			    output_table.add(columns);
			}
			ResultSetMetaData meta = rs.getMetaData();
			colCount = meta.getColumnCount();
			int size = 0;
			rs.last();
		    size = rs.getRow();
		    rs.beforeFirst();
			rowCount= size;
			
			} catch (SQLException e) {
				errorMessage = e.getMessage();
				errorFlag=true;
			}
		catch (Exception e) {
			errorMessage = e.getMessage();
			errorFlag=true;
			}
			displayTable=true;
			return "SUCCESS";

		
		
	}

	public String TableCopy(){
		
		try {
			
			output_table = new ArrayList<List<Object>>();

			query = "select * from "+selected_schema+"."+selectedTable;
			ResultSet rs = databaseConnectionBean.readFromDatabase(query);
			ResultSetMetaData result_meta = rs.getMetaData();
			setOutColList(result_meta);
			
			int columnCount = result_meta.getColumnCount();

			while (rs.next()) {
			  
				
			    List<Object> columns = new ArrayList<Object>();

			    for (int i = 1; i <= columnCount; i++) {
			        columns.add(rs.getObject(i));
			        
			    }

			    output_table.add(columns);
			}
			
			
			} catch (SQLException e) {
				errorMessage = e.getMessage();
				errorFlag=true;
			}
			displayTable=true;
			return "SUCCESS";

		
		
	}

	public String DisplaySelColumn(){
		
		
		try {
			
			List<String> temp_list = new ArrayList<String>();
			
            if(selectedColumns ==null|| selectedColumns.isEmpty()){

            }else{
			for(String s : selectedColumns){
				String[] temp = s.split(" : ");
				temp_list.add(temp[0]);
			}
			String columns_query=String.join(",", temp_list);
			
			output_table = new ArrayList<List<Object>>();
			query = "select "+columns_query+" from "+selected_schema+"."+selectedTable;
			ResultSet rs = databaseConnectionBean.readFromDatabase(query);
			ResultSetMetaData result_meta = rs.getMetaData();
			setOutColList(result_meta);
			
			int columnCount = result_meta.getColumnCount();

			
			
			while (rs.next()) {
			    List<Object> columns = new ArrayList<Object>();

			    for (int i = 1; i <= columnCount; i++) {
			        columns.add(rs.getObject(i));
			    }

			    output_table.add(columns);
			}
			
			
            }
			} catch (SQLException e) {
				errorMessage = e.getMessage();
				errorFlag=true;
			}
			displayTable=true;
			return "SUCCESS";


	}


	public String CreateTable(){
		query = "";
		
		if(selectedTable.equalsIgnoreCase("country")){
			query = "CREATE TABLE IF NOT EXISTS `country`"
					+ " (  `Code` char(3) NOT NULL DEFAULT '',"
					+ "  `Name` char(52) NOT NULL DEFAULT '',"
					+ "  `Continent` "
					+ "enum('Asia','Europe','North America','Africa',"
					+ "'Oceania','Antarctica','South America') NOT NULL "
					+ "DEFAULT 'Asia',  `Region` char(26) NOT NULL DEFAULT '',"
					+ "  `SurfaceArea` float(10,2) NOT NULL DEFAULT '0.00', "
					+ " `IndepYear` smallint(6) DEFAULT NULL, "
					+ " `Population` int(11) NOT NULL DEFAULT '0',"
					+ "  `LifeExpectancy` float(3,1) DEFAULT NULL,  "
					+ "`GNP` float(10,2) DEFAULT NULL, "
					+ " `GNPOld` float(10,2) DEFAULT NULL,"
					+ "  `LocalName` char(45) NOT NULL DEFAULT '',"
					+ "  `GovernmentForm` char(45) NOT NULL DEFAULT '', "
					+ " `HeadOfState` char(60) DEFAULT NULL,  `Capital` int(11)"
					+ " DEFAULT NULL,  `Code2` char(2) NOT NULL DEFAULT '',  "
					+ "PRIMARY KEY (`Code`)) ENGINE=InnoDB DEFAULT CHARSET=latin1";	
		    		
		}

		if(selectedTable.equalsIgnoreCase("city")){

		    		
		    		query =  "CREATE TABLE IF NOT EXISTS `city` ("
		    				+ "  `ID` int(11) NOT NULL AUTO_INCREMENT, "
		    				+ " `Name` char(35) NOT NULL DEFAULT '',"
		    				+ "  `CountryCode` char(3) NOT NULL DEFAULT '', "
		    				+ " `District` char(20) NOT NULL DEFAULT '', "
		    				+ " `Population` int(11) NOT NULL DEFAULT '0', "
		    				+ " PRIMARY KEY (`ID`),  KEY `CountryCode` "
		    				+ "(`CountryCode`),  CONSTRAINT `city_ibfk_1`"
		    				+ " FOREIGN KEY (`CountryCode`) REFERENCES `country` "
		    				+ "(`Code`)) ENGINE=InnoDB AUTO_INCREMENT=4080 "
		    				+ "DEFAULT CHARSET=latin1;";
		    
		}

		if(selectedTable.equalsIgnoreCase("countrylanguage")){
	
		    		query =  "CREATE TABLE IF NOT EXISTS `countrylanguage` ( "
		    				+ " `CountryCode` char(3) NOT NULL DEFAULT '',"
		    				+ "  `Language` char(30) NOT NULL DEFAULT '',"
		    				+ "  `IsOfficial` enum('T','F') NOT NULL DEFAULT 'F',"
		    				+ "  `Percentage` float(4,1) NOT NULL DEFAULT '0.0', "
		    				+ " PRIMARY KEY (`CountryCode`,`Language`),"
		    				+ "  KEY `CountryCode` (`CountryCode`),"
		    				+ "  CONSTRAINT `countryLanguage_ibfk_1` FOREIGN KEY"
		    				+ " (`CountryCode`) REFERENCES `country` (`Code`)) "
		    				+ "ENGINE=InnoDB DEFAULT CHARSET=latin1;";

		}

		databaseConnectionBean.execute(query);
		selected_schema = "connect";
		DisplayTableList();
		return "SUCCESS";
	}
	
	public String DropTables(){
		String query1 = "drop table "+selectedTable;
		databaseConnectionBean.execute(query1);	
		System.out.println("dropped");
		DisplayTableList();
		return "SUCCESS";
	}
	
	public String DisplayDescriptiveStats(){
		
		try {
			
			List<String> temp_list = new ArrayList<String>();
			
            if(selectedColumns ==null|| selectedColumns.isEmpty()){
             	output_table = new ArrayList<List<Object>>();
	
            }else{
			for(String s : selectedColumns){
				String[] temp = s.split(" : ");
				temp_list.add(temp[0]);
			}
			String columns_query=String.join(",", temp_list);
		//	System.err.println(columns_query);
			
			output_table = new ArrayList<List<Object>>();
			query = "select "+columns_query+" from "+selected_schema+"."+selectedTable;
			query1 = "select "+columns_query+" from "+selected_schema+"."+selectedTable;
		
			//System.out.println(query);
			ResultSet rs = databaseConnectionBean.readFromDatabase(query);
			
			
			ResultSet rs1 = databaseConnectionBean.readFromDatabase(query1);
		ResultSetMetaData result_meta = rs.getMetaData();
		setOutColList(result_meta);
		
			int countNew=0;
			  while(rs1.next()){
				    countNew=countNew+1;
			  
			  }
			  
			//System.out.println(countNew);
			int count = countNew;
			
			double stat[] = new double [count];
		 
			

			int i = 0;
			while (rs.next()) {     
			//	System.out.println("1 - " +rs.getDouble(columns_query));
		       stat [i++]=rs.getDouble(columns_query);
		
		        }
			
		//	System.out.println(stat[1]);
		
			minValue = StatUtils.min(stat);
			maxValue = StatUtils.max(stat);
			mean = StatUtils.mean(stat);
			variance = StatUtils.variance(stat, mean);
			std = Math.sqrt(variance);
			median = StatUtils.percentile(stat, 50.0);
			q1 = StatUtils.percentile(stat, 25.0);
			q3 = StatUtils.percentile(stat, 75.0);

			/*
			System.out.println("MINIMUM = "+minValue);
			System.out.println("MAXIMUM = "+maxValue);
			System.out.println("MEAN = "+mean);
			System.out.println("VARIANCE = "+variance);
			System.out.println("STD = "+std);
			System.out.println("MEDIAN = "+median);
			System.out.println("PERCENTILE1 = "+q1);
			System.out.println("PERCENTILE2 = "+q3);
			
			
			*/
			}
            
		}catch (Exception e) {
				errorMessage = e.getMessage();
				errorFlag=true;
			}
			displayTable=true;
			return "SUCCESS";

		
            }
	
	public void Export(){
		ExportCsv(selectedTable +".csv");
		
	}
	
	public String ExportCsv(String fileName){
		String successMessage="";
		try{

	        FacesContext fc = FacesContext.getCurrentInstance();
	        ExternalContext ec = fc.getExternalContext();	        
	        ec.responseReset(); 	        
	        ec.setResponseContentType("application/vnd.csv"); 	        
	        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); 
	        OutputStream output = ec.getResponseOutputStream();
			String query = "SELECT * FROM "+selected_schema+"."+selectedTable;
			ResultSet rs = databaseConnectionBean.readFromDatabase(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			for(int i = 1;i<=rsmd.getColumnCount();i++){
				output.write(rsmd.getColumnName(i).getBytes());
				output.write(',');
			}
			output.write('\n');

			while(rs.next()){
				for(int j =1;j<=rsmd.getColumnCount();j++){
					if(rs.getString(j)== null)
					{
						output.write(" ".getBytes());
					}
					else
					{
					output.write(rs.getString(j).getBytes());
					}
					output.write(',');
				}
				output.write('\n');
			}			
			output.flush();
			output.close();
		    fc.responseComplete(); 			

			successMessage = "File has been downloaded successfully";
		}
		catch(Exception e){
			successMessage = "Unable to export file. Please try again.";
			e.printStackTrace();
		}
		return successMessage;
		
		
		
	}
	
	
	
	

	public String displayCharts() {
		
		errorMessage = "";
		graph_flag = false;
		
		ArrayList<String> selectedColumnListNoInfo = new ArrayList<String>();
		
		if (selectedTable.equals("")) {
			errorMessage = "Please select a table";
			return "Done";
		}
		


		for (int i = 0; i < selectedColumns.size(); i++) {
			
			StringTokenizer st = new StringTokenizer(selectedColumns.get(i));
			selectedColumnListNoInfo.add(st.nextToken());
		}
		
		try{

			if(selectedTable.equals("city")&&selectedColumnListNoInfo.size()==1&&selectedColumnListNoInfo.get(0).equalsIgnoreCase("CountryCode")){
			
				String query = "Select CountryCode, COUNT(*) from "+selected_schema+".city GROUP BY CountryCode";
				ResultSet rs = null;
				rs = databaseConnectionBean.readFromDatabase(query);
				ArrayList<String> country_code = new ArrayList<String>();
				ArrayList<Integer> count = new ArrayList<Integer>();
				
			
				while (rs.next()) {
					country_code.add(rs.getString(1));
					count.add(rs.getInt(2));
					
				}
				Random rnd = new Random();
				int n = 105000 + rnd.nextInt(805000);

				graph_name = "chart_"+n+".png";
				graph_flag = true;
				System.out.println(graph_name);
				 DefaultPieDataset data = new DefaultPieDataset();
					FacesContext fc = FacesContext.getCurrentInstance();
					String path = fc.getExternalContext().getRealPath("/temp");
					
					String fileName = path + "/" + graph_name;
					
					File out = new File(fileName);
					

				 for (int i = 0; i < country_code.size(); i++) {
					 data.setValue(country_code.get(i)+"("+count.get(i)+")", count.get(i));
					}

				 JFreeChart chart = ChartFactory.createPieChart(
						 graph_name, data, true, true, false
				 );
			
				 try {
					ChartUtilities.saveChartAsPNG(out, chart, 800, 600);

				 } catch (Exception e) {
					 e.printStackTrace();
				}

							return "Done";

			}	

				else if(selectedTable.equals("country")&&selectedColumnListNoInfo.size()==1&&
					(selectedColumnListNoInfo.get(0).equalsIgnoreCase("Population")||
							selectedColumnListNoInfo.get(0).equalsIgnoreCase("SurfaceArea")||
							selectedColumnListNoInfo.get(0).equalsIgnoreCase("LifeExpectancy")||
							selectedColumnListNoInfo.get(0).equalsIgnoreCase("GNP")||
							selectedColumnListNoInfo.get(0).equalsIgnoreCase("GNPOld"))){

				String query = "Select "+selectedColumnListNoInfo.get(0)+", Name from "+selected_schema+".country ORDER BY "+selectedColumnListNoInfo.get(0)+" DESC LIMIT 3";
				ResultSet rs = null;
				rs = databaseConnectionBean.readFromDatabase(query);
				
				ArrayList<String> continent_name = new ArrayList<String>();
				ArrayList<Integer> count = new ArrayList<Integer>();
				
				
			
				while (rs.next()) {
					
					continent_name.add(rs.getString(2));
					count.add(rs.getInt(1));
				
								
				}
				
				Random rnd = new Random();
				int n = 100500 + rnd.nextInt(800500);

				graph_name = "chart_"+n+".png";
				graph_flag = true;


				 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				 FacesContext fc = FacesContext.getCurrentInstance();
					String path = fc.getExternalContext().getRealPath("/temp");
					String fileName = path + "/" + graph_name;
					File file = new File(fileName);
					file.delete();
					
					File out = new File(fileName);
				 
					 for (int i = 0; i < continent_name.size(); i++) {
						 dataset.addValue(count.get(i),"", continent_name.get(i)+"("+count.get(i)+")");

						}

				 
				 
				 JFreeChart chart = ChartFactory.createBarChart3D(
						 graph_name,
				"Country",
				selectedColumnListNoInfo.get(0),
				 dataset,
				 PlotOrientation.VERTICAL,
				 true,
				 true,
				 false
				 );

				 try {
						ChartUtilities.saveChartAsPNG(out, chart, 1000, 800);
		//System.out.println("done/...");
					 } catch (IOException e) {
					//	 e.printStackTrace();
						// System.out.println("error");
					}
				 
				return "Done";

			}

			else if(selectedTable.equals("city")&&selectedColumnListNoInfo.size()==1&&
					(selectedColumnListNoInfo.get(0).equalsIgnoreCase("Population"))){
		
				String query = "Select "+selectedColumnListNoInfo.get(0)+", Name from "+selected_schema+".city ORDER BY "+selectedColumnListNoInfo.get(0)+" DESC LIMIT 5";
				ResultSet rs = null;
				rs = databaseConnectionBean.readFromDatabase(query);

				ArrayList<String> continent_name = new ArrayList<String>();
				ArrayList<Integer> count = new ArrayList<Integer>();
				
				
			
				while (rs.next()) {
					continent_name.add(rs.getString(2));
					count.add(rs.getInt(1));
								
				}
			
				Random rnd = new Random();
				int n = 100500 + rnd.nextInt(800500);

				graph_name = "chart_"+n+".png";
				graph_flag = true;

	
				 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				 FacesContext fc = FacesContext.getCurrentInstance();
					String path = fc.getExternalContext().getRealPath("/temp");
					String fileName = path + "/" + graph_name;
					File file = new File(fileName);
					file.delete();
					
					File out = new File(fileName);
				 
					 for (int i = 0; i < continent_name.size(); i++) {
						 dataset.addValue(count.get(i),"", continent_name.get(i)+"("+count.get(i)+")");

						}

				 
				 
				 JFreeChart chart = ChartFactory.createBarChart3D(
						 graph_name,
		         "City",
				 selectedColumnListNoInfo.get(0),
				 dataset,
				 PlotOrientation.VERTICAL,
				 true,
				 true,
				 false
				 );

				 try {
						ChartUtilities.saveChartAsPNG(out, chart, 1000, 800);
		//System.out.println("done/...");
					 } catch (IOException e) {
					//	 e.printStackTrace();
						// System.out.println("error");
					}
				 
				return "Done";

			}
						
			else if(selectedColumnListNoInfo.size()==2&&selectedTable.equals("countrylanguage")&&(
					(selectedColumnListNoInfo.get(0).equalsIgnoreCase("Language")&&selectedColumnListNoInfo.get(1).equalsIgnoreCase("Percentage"))||
					(selectedColumnListNoInfo.get(1).equalsIgnoreCase("Language")&&selectedColumnListNoInfo.get(0).equalsIgnoreCase("Percentage")))){
				
					
				String query = "Select Percentage, Language from "+selected_schema+".countrylanguage ORDER BY "+selectedColumnListNoInfo.get(0)+" DESC LIMIT 5";
				ResultSet rs = null;
				rs = databaseConnectionBean.readFromDatabase(query);
				
				ArrayList<String> continent_name = new ArrayList<String>();
				ArrayList<Integer> count = new ArrayList<Integer>();
				
				
			
				while (rs.next()) {
					continent_name.add(rs.getString(2));
					count.add((int)rs.getFloat(1));
								
				}
			
				Random rnd = new Random();
				int n = 100500 + rnd.nextInt(800500);

				graph_name = "chart_"+n+".png";
				graph_flag = true;

				 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				 FacesContext fc = FacesContext.getCurrentInstance();
					String path = fc.getExternalContext().getRealPath("/temp");
					String fileName = path + "/" + graph_name;
					File file = new File(fileName);
					file.delete();
					
					File out = new File(fileName);
				 
					 for (int i = 0; i < continent_name.size(); i++) {
						 dataset.addValue(count.get(i),"", continent_name.get(i)+"("+count.get(i)+")");

						}

				 
				 
				 JFreeChart chart = ChartFactory.createBarChart3D(
						 graph_name,
				 "Language",
				 "Percentage",
				 dataset,
				 PlotOrientation.VERTICAL,
				 true,
				 true,
				 false
				 );

				 try {
						ChartUtilities.saveChartAsPNG(out, chart, 1000, 800);
		//System.out.println("done/...");
					 } catch (IOException e) {
					//	 e.printStackTrace();
						// System.out.println("error");
					}
				 
				return "Done";

				
			}
			else{
						errorMessage = "select column";
							return "Done";

						}



			
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}


		return "Done";

	}	
	

	
		}
		


