package com.example.flexsaless.Service;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.ExcelFile;
import com.example.flexsaless.Entitiy.Product;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.ProductRepository;
import com.example.flexsaless.Repository.StorageFileRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    private StorageFileRepository storageFileRepository;
    @Autowired
    private ProductRepository productRepository;

    public Client getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            Optional<Client> currentUserNamee = (Optional<Client>) authentication.getPrincipal();
            Client client = currentUserNamee.get();
            return client;
        }
        return null;
    }

    public ClientService(ClientRepository clientRepository, StorageFileRepository storageFileRepository) {
        this.clientRepository = clientRepository;
        this.storageFileRepository = storageFileRepository;
    }


    public Client save(Client client) {
        return this.clientRepository.save(client);
    }

    public ExcelFile uploadFile(MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            Optional<Client> currentUserNamee = (Optional<Client>) authentication.getPrincipal();
            Client client = currentUserNamee.get();

            String filename = file.getOriginalFilename();
            ExcelFile excelFile = new ExcelFile(filename, file.getContentType(), file.getBytes(), client);
            client.setExcelFile(excelFile);
            ExcelFile savedFile = this.storageFileRepository.save(excelFile);

            List<Product> products = new ArrayList<>();

            byte[] fileBytes = excelFile.getData();

           ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);
                 Workbook workbook = new XSSFWorkbook(inputStream);
                Sheet sheet = workbook.getSheetAt(0);

                List<Row> rows = (List<Row>) toList(sheet.iterator());
                rows.remove(0);
                rows.forEach(row -> {
                    List<Cell> cells = (List<Cell>) toList(row.cellIterator());

                    Product productReaded = Product.builder().code(cells.get(0).getStringCellValue())
                            .name(cells.get(1).getStringCellValue())
                            .commission(2.25)
                            .taxes(4.55).build();

                    productRepository.save(productReaded);
                    products.add(productReaded);
                });


                Iterator<Row> iterator = sheet.iterator();

                client.setProductsList(products);
                this.clientRepository.save(client);
                return savedFile;

        }
      return null;
    }

    public List<?> toList(Iterator<?> iterator) {
        return IteratorUtils.toList(iterator);
    }

}
