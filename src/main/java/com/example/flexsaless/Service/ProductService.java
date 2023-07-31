package com.example.flexsaless.Service;

import com.example.flexsaless.Entitiy.ExcelFile;
import com.example.flexsaless.Entitiy.Product;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import lombok.Cleanup;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
             Workbook workbook = new HSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            List<Row> rows = (List<Row>) toList(sheet.iterator());

            toList(sheet.iterator());


            Iterator<Row> iterator = sheet.iterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<?> toList(Iterator<?> iterator){
        return IteratorUtils.toList(iterator);
    }
}
