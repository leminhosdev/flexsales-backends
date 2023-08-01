package com.example.flexsaless.Service;

import com.example.flexsaless.Entitiy.ExcelFile;
import com.example.flexsaless.Entitiy.Product;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import lombok.Cleanup;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private StorageFileRepository storageFileRepository;
    @Autowired
    private ClientRepository clientRepository;

    public ExcelFile aa(){
        List<ExcelFile> all = this.storageFileRepository.findAll();
        return all.get(0);
    }

    public List<Product> criar() throws IOException {
        List<Product> products = new ArrayList<>();
        List<ExcelFile> table = this.storageFileRepository.findAll();
        byte[] fileBytes = table.get(0).getData();

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            List<Row> rows = (List<Row>) toList(sheet.iterator());

            rows.forEach(row ->{
                List<Cell> cells = (List<Cell>) toList(row.cellIterator());

                Product productReaded = Product.builder().code(cells.get(0).getStringCellValue())
                        .name(cells.get(1).getStringCellValue())
                        .commission(cells.get(2).getNumericCellValue())
                        .taxes(cells.get(3).getNumericCellValue()).build();


                products.add(productReaded);
            });


            Iterator<Row> iterator = sheet.iterator();
            return products;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<?> toList(Iterator<?> iterator){
        return IteratorUtils.toList(iterator);
    }
}
