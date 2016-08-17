package com.congun.web.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.congun.web.dao.ContractorRequirementQuoteDAO;
import com.congun.web.dao.MachineDAO;
import com.congun.web.dao.SupplierQuoteDao;
import com.congun.web.model.AddEquipment;
import com.congun.web.model.ContractorRequirement;
import com.congun.web.model.Machines;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public class CongunXLParser {

	@Autowired
    MachineDAO machinedao;

	@Autowired
	static ContractorRequirementQuoteDAO requirementdao;

	@Autowired
	static SupplierQuoteDao quotedao;

/*	private static final String FILE_PATH = "C:\\Users\\Nishant\\Desktop\\congun\\SampleExcel.xlsx";

	public static void main(String args[]) {

		// List studentList =
		 getMachinesList(FILE_PATH);

		// System.out.println(studentList);
	}*/

	public void getMachinesList(String path) {
		boolean duplicate = false;
		System.out.println(path);
		// List machineList = new ArrayList();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
            List<Machines> list = machinedao.getDistinctModels();
			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(fis);

			int numberOfSheets = workbook.getNumberOfSheets();

			// looping over each workbook sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator rowIterator = sheet.iterator();
				int rowcount = 0;
				// iterating over each row  
				System.out.println("Initial Row Count :"+rowcount);
				while (rowIterator.hasNext()) {
					Machines machine = new Machines();
					Row row = (Row) rowIterator.next();
					Iterator cellIterator = row.cellIterator();
                    rowcount = row.getRowNum();
                    if(rowcount >0){
					// Iterating over each cell (column wise) in a particular
					// row.
					while (cellIterator.hasNext()) {

						Cell cell = (Cell) cellIterator.next();
						// The Cell Containing String will is name.
						if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
							 //System.out.println(cell.getStringCellValue());

							if (cell.getColumnIndex() == 0) {
								machine.setCategory(cell.getStringCellValue());
								System.out.println("Category :"+machine.getCategory());
							}
							// Cell with index 2 contains marks in Science
							else if (cell.getColumnIndex() == 1) {

								machine.setEquipment(cell.getStringCellValue());
								System.out.println("Equipment :"+machine.getEquipment());
							}
							// Cell with index 3 contains marks in English
							else if (cell.getColumnIndex() == 2) {
								if(list.contains(cell.getStringCellValue())){
									duplicate = true;
								}
								else{
									duplicate = false;
								}
								machine.setModel(cell.getStringCellValue());
								System.out.println("Model :"+machine.getModel());
							} else if (cell.getColumnIndex() == 3) {
								machine.setMake(cell.getStringCellValue());
								System.out.println("Make :"+machine.getMake());
							} else if (cell.getColumnIndex() == 4) {
								machine.setCapacity(cell.getStringCellValue());
								System.out.println("Capacity :"+machine.getCapacity());
							}
						}
					}
					if(machinedao == null){
						System.out.println("MachineDAO object is null");
					}else
						System.out.println("MachineDAO is not null");
					if(duplicate==false){
						if (machinedao.insertMachineDetails(machine).equals(
								ResponseConstants.MACHINE_SUCCESS_CODE)) {
							//rowcount = rowcount+1;
							System.out.println("Machine at Row Count:" + rowcount + " has been added successfully!!");
						} else {
							System.out
									.println("Exception Occured while adding Machine at row: "
											+ rowcount);
						}
					}

                    }
					//rowcount++;
				}
			}

			fis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		// return studentList;
	}

	public static void updateRequirementsFromExcel(String path) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);

			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(fis);

			int numberOfSheets = workbook.getNumberOfSheets();

			// looping over each workbook sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator rowIterator = sheet.iterator();
				int rowcount = 0;
				// iterating over each row
				while (rowIterator.hasNext()) {

					ContractorRequirement requirement = new ContractorRequirement();
					Row row = (Row) rowIterator.next();
					Iterator cellIterator = row.cellIterator();

					// Iterating over each cell (column wise) in a particular
					// row.
					while (cellIterator.hasNext()) {

						Cell cell = (Cell) cellIterator.next();

						if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
							System.out.println(cell.getStringCellValue());

							if (cell.getColumnIndex() == 2) {
								requirement.setTitle(cell.getStringCellValue());

							}
							// Cell with index 2 contains marks in Science
							else if (cell.getColumnIndex() == 3) {
								requirement.setContractorName(cell
										.getStringCellValue());
							}
							// Cell with index 3 contains marks in English
							else if (cell.getColumnIndex() == 4) {
								requirement.setEquipmentCategory(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 5) {
								requirement.setEquipmentName(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 6) {
								requirement.setSpecificationCapacity(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 7) {
								requirement.setManufacturer(cell
										.getStringCellValue().split(","));
							} else if (cell.getColumnIndex() == 8) {
								requirement.setSpecificationModel(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 11) {
								requirement.setStartDate(new Date(cell
										.getStringCellValue()));
							} else if (cell.getColumnIndex() == 13) {
								requirement.setHiringChargesType(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 14) {
								requirement.setWorkLocation(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 15) {
								requirement.setTransportation(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 16) {
								requirement.setDriverNeeded(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 17) {
								requirement.setHelperNeeded(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 18) {
								requirement.setDieselCharges(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 19) {
								requirement.setWorkDescription(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 20) {
								requirement.setWorkAreaImages(cell
										.getStringCellValue());
							}
							// The Cell Containing numeric value will contain
							// marks
						} else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
							// System.out.println("Output"+cell.getNumericCellValue());

							if (cell.getColumnIndex() == 1) {
								requirement.setContractorId((long) cell
										.getNumericCellValue());
							}

							else if (cell.getColumnIndex() == 9) {
								requirement.setQuantity((int) cell
										.getNumericCellValue());
							}

							else if (cell.getColumnIndex() == 10) {
								requirement.setDuration((int) cell
										.getNumericCellValue());
							}

							else if (cell.getColumnIndex() == 12) {
								requirement
										.setEquipmentManufactureYear((int) cell
												.getNumericCellValue());
							}

						}
					}

					if (requirementdao.saveRequirement(requirement).equals(
							ResponseConstants.CONTRACTOR_SUCCESS_CODE)) {
						System.out.println("Requirement at Row Count:"
								+ rowcount + 1
								+ " has been added successfully!!");
						// System.out.println("Requirement for Id:"+requirement.getContractorId()+" Category: "+requirement.getEquipmentCategory()+" Equipment: "+requirement.getEquipmentName());
					} else {
						System.out
								.println("Exception Occured while adding requirement at row:"
										+ rowcount + 1);
					}
					rowcount++;
				}

				fis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return studentList;
	}

	public static void updateEquipmentsFromExcel(String path) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);

			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(fis);

			int numberOfSheets = workbook.getNumberOfSheets();

			// looping over each workbook sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator rowIterator = sheet.iterator();
				int rowcount = 0;
				// iterating over each row
				while (rowIterator.hasNext()) {

					AddEquipment equipment = new AddEquipment();
					Row row = (Row) rowIterator.next();
					Iterator cellIterator = row.cellIterator();

					// Iterating over each cell (column wise) in a particular
					// row.
					while (cellIterator.hasNext()) {

						Cell cell = (Cell) cellIterator.next();

						if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
							System.out.println(cell.getStringCellValue());

							if (cell.getColumnIndex() == 2) {
								equipment.setSupplierName(cell
										.getStringCellValue());

							}
							// Cell with index 2 contains marks in Science
							else if (cell.getColumnIndex() == 3) {
								equipment.setEquipmentCategory(cell
										.getStringCellValue());
							}

							else if (cell.getColumnIndex() == 4) {
								equipment.setEquipment(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 5) {
								equipment.setManufacturer(cell
										.getStringCellValue());
							} else if (cell.getColumnIndex() == 7) {
								equipment.setModel(cell.getStringCellValue());
							} else if (cell.getColumnIndex() == 8) {
								equipment
										.setCapacity(cell.getStringCellValue());
							}
							// The Cell Containing numeric value will contain
							// marks
						} else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
							// System.out.println("Output"+cell.getNumericCellValue());

							if (cell.getColumnIndex() == 1) {
								equipment.setSupplierId((long) cell
										.getNumericCellValue());
							}

							else if (cell.getColumnIndex() == 6) {
								equipment.setYearOfManufacturing((int) cell
										.getNumericCellValue());
							}

							else if (cell.getColumnIndex() == 9) {
								equipment.setQuantity((int) cell
										.getNumericCellValue());
							}

						}
					}
					if (quotedao.addEquipment(equipment).equals(
							ResponseConstants.SUPPLIER_SUCCESS_CODE)) {
						System.out.println("Equipment at Row Count:" + rowcount
								+ 1 + " has been added successfully!!");
						/*
						 * System.out.println("Equpimemt with SupplierId :" +
						 * equipment.getSupplierId() + " : Make - " +
						 * equipment.getManufacturer() + " Model - " +
						 * equipment.getModel() +
						 * " has been added Successfully");
						 */
					} else {
						System.out
								.println("Exception Occured while adding Equipment at row:"
										+ rowcount + 1);
					}
					rowcount++;
				}

				fis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
