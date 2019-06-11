package com.maxwell.classcreator.control;

import com.maxwell.classcreator.model.Json;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Maxwell Knoxx - 06/2019
 */
public class JsonController {

    /**
     *
     * @param json
     * @return
     */
    public Boolean generateFiles(Json json) {

        json = getJsonFields(json);

        if (json.getCreateEntity()) {
            createEntity(json);
        }
        if (json.getCreateModel()) {
            createModel(json);
        }
        if (json.getCreateRepository()) {
            createRepository(json);
        }
        if (json.getCreateServiceImpl()) {
            createService(json);
            createServiceImpl(json);
        }

        return true;
    }

    /**
     *
     * @param json
     * @return
     */
    public Json getJsonFields(Json json) {
        List<String> listFields = new ArrayList<>();
        String jsonText = json.getText();

        String[] fields = jsonText.split(",");

        listFields.addAll(Arrays.asList(fields));

        json.setFields(listFields);

        return json;
    }

    /**
     * Class Type 0
     *
     * @param json
     * @return
     */
    private Boolean createEntity(Json json) {
        StringBuilder sb = new StringBuilder();

        sb.append(generateImports(0));
        sb.append(generateClassAnotation(0, json.getClassName()));
        sb.append(generateClassName(0, json.getClassName()));
        sb.append(generateFields(0, json));
        sb.append(generateGettersAndSetters(json));
        sb.append(closeCurlyBracket());

        System.out.println(sb.toString());
        saveToFile(sb, json.getPath(), json.getClassName()+"Entity");

        return true;
    }

    /**
     * Class Type 1
     *
     * @param json
     * @return
     */
    private Boolean createModel(Json json) {
        StringBuilder sb = new StringBuilder();

        sb.append(generateImports(1));
        sb.append(generateClassAnotation(1, json.getClassName()));
        sb.append(generateClassName(1, json.getClassName()));
        sb.append(generateFields(1, json));
        sb.append(generateGettersAndSetters(json));
        sb.append(closeCurlyBracket());

        System.out.println(sb.toString());
        saveToFile(sb, json.getPath(), json.getClassName()+"Model");

        return true;
    }

    /**
     * Class type 2
     *
     * @param json
     * @return
     */
    private Boolean createRepository(Json json) {
        StringBuilder sb = new StringBuilder();

        String className = json.getClassName();

        sb.append(generateImports(2));
        sb.append(generateClassAnotation(2, className));
        sb.append(generateClassName(2, className));
        sb.append(generateMethods(2, className));
        sb.append(closeCurlyBracket());

        System.out.println(sb.toString());
        saveToFile(sb, json.getPath(), json.getClassName() + "Repository");

        return true;
    }

    /**
     * Class type 3
     *
     * @param json
     * @return
     */
    private Boolean createService(Json json) {
        StringBuilder sb = new StringBuilder();

        String className = json.getClassName();

        sb.append(generateImports(3));
        sb.append(generateClassAnotation(3, className));
        sb.append(generateClassName(3, className));
        sb.append(generateMethods(3, className));
        sb.append(closeCurlyBracket());

        System.out.println(sb.toString());
        saveToFile(sb, json.getPath(), json.getClassName() + "Service");

        return true;
    }

    /**
     * Class type 4
     *
     * @param json
     * @return
     */
    private Boolean createServiceImpl(Json json) {
        StringBuilder sb = new StringBuilder();

        String className = json.getClassName();

        sb.append(generateImports(4));
        sb.append(generateClassAnotation(4, className));
        sb.append(generateClassName(4, className));
        sb.append(generateAutowiredAnnotation(className));
        sb.append(generateMethods(4, className));
        sb.append(closeCurlyBracket());

        System.out.println(sb.toString());
        saveToFile(sb, json.getPath(), json.getClassName() + "ServiceImpl");

        return true;
    }

    /**
     *
     * @param classType 0 - Entity, 1 - Model, 2 - Repository, 3 - Service, 4 -
     * ServiceImpl
     * @return
     */
    public String generateImports(int classType) {
        StringBuilder sb = new StringBuilder();

        switch (classType) {
            case 0:
                sb.append("import javax.persistence.Column;").append("\n");
                sb.append("import javax.persistence.Entity;").append("\n");
                sb.append("import javax.persistence.GeneratedValue;").append("\n");
                sb.append("import javax.persistence.GenerationType;").append("\n");
                sb.append("import javax.persistence.Id;").append("\n");
                sb.append("import javax.persistence.Table;").append("\n");
                break;
            case 2:
                sb.append("import java.util.List;").append("\n");
                sb.append("import java.util.Optional;").append("\n");
                sb.append("import org.springframework.data.jpa.repository.JpaRepository;").append("\n");
                sb.append("import org.springframework.stereotype.Repository;").append("\n");
                break;
            case 3:
                sb.append("import java.util.List;").append("\n");
                sb.append("import java.util.Optional;").append("\n");
                break;
            case 4:
                sb.append("import java.util.List;").append("\n");
                sb.append("import java.util.Optional;").append("\n");
                sb.append("import org.springframework.beans.factory.annotation.Autowired;").append("\n");
                sb.append("import org.springframework.stereotype.Service;").append("\n");
                break;
            default:
                break;
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     *
     * @param classType 0 - Entity, 1 - Model, 2 - Repository, 3 - Service, 4 -
     * ServiceImpl
     * @param className
     * @return
     */
    public String generateClassAnotation(int classType, String className) {
        StringBuilder sb = new StringBuilder();

        switch (classType) {
            case 0:
                sb.append("@Entity").append("\n");
                sb.append("@Table(name = \"").append(firstLetterLowerCase(className)).append("\") \n");
                break;
            case 2:
                sb.append("@Repository").append("\n");
                break;
            case 4:
                sb.append("@Service").append("\n");
                break;
            default:
                break;
        }

        return sb.toString();
    }

    /**
     *
     * @param classType 0 - Entity, 1 - Model, 2 - Repository, 3 - Service, 4 -
     * ServiceImpl
     * @param className
     * @return
     */
    public String generateClassName(int classType, String className) {
        StringBuilder sb = new StringBuilder();

        switch (classType) {
            case 0:
                sb.append("public class ").append(className).append("Entity {").append("\n");
                break;
            case 1:
                sb.append("public class ").append(className).append("Model {").append("\n");
                break;
            case 2:
                sb.append("public interface ").append(className).append("Repository extends JpaRepository<").append(className).append("Entity, Long> {").append("\n");
                break;
            case 3:
                sb.append("public interface ").append(className).append("Service {").append("\n");
                break;
            case 4:
                sb.append("public class ").append(className).append("ServiceImpl implements ").append(className).append("Service {").append("\n");
                break;
            default:
                break;
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     *
     * @param classType 0 - Entity, 1 - Model
     * @param json
     * @return
     */
    public String generateFields(int classType, Json json) {
        StringBuilder sb = new StringBuilder();

        String type;
        String fieldName;

        switch (classType) {
            case 0:
                for (String field : json.getFields()) {
                    type = getFieldType(field);
                    fieldName = getFieldName(field);
                    if ("id".equals(fieldName)) {
                        sb.append("@Id \n @GeneratedValue(strategy = GenerationType.AUTO) \n private ").append(type).append(" ").append(fieldName).append("; \n");
                    } else {
                        sb.append("@Column(name = \"").append(removeCamelCaseField(fieldName)).append("\" , nullable = false) \n");
                        sb.append("private ").append(type).append(" ").append(fieldName).append("; \n\n");
                    }
                }
                break;
            case 1:
                for (String field : json.getFields()) {
                    type = getFieldType(field);
                    fieldName = getFieldName(field);
                    sb.append("private ").append(type).append(" ").append(fieldName).append("; \n");
                }
                break;
            default:
                break;
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     *
     * @param json
     * @return
     */
    public String generateGettersAndSetters(Json json) {
        StringBuilder sb = new StringBuilder();

        String type;
        String fieldName;

        for (String field : json.getFields()) {
            type = getFieldType(field);
            fieldName = getFieldName(field);
            sb.append(generateGetters(type, fieldName)).append("\n\n");
            sb.append(generateSetters(type, fieldName)).append("\n\n");
        }

        return sb.toString();
    }

    /**
     *
     * @param classType 2 - Repository, 3 - Service, 4 - ServiceImpl
     * @param className
     * @return
     */
    public String generateMethods(int classType, String className) {
        StringBuilder sb = new StringBuilder();

        switch (classType) {
            case 2:
                sb.append("List<").append(className).append("Entity> findAll();").append("\n\n");
                sb.append("Optional<").append(className).append("Entity> findById(Long id);").append("\n\n");
                break;
            case 3:
                sb.append("List<").append(className).append("Entity> findAll();").append("\n\n");
                sb.append("Optional<").append(className).append("Entity> findById(Long id);").append("\n\n");
                sb.append(className).append("Entity add").append(className).append("(").append(className).append("Entity ").append(firstLetterLowerCase(className)).append("); \n\n");
                sb.append(className).append("Entity update").append(className).append("(").append(className).append("Entity ").append(firstLetterLowerCase(className)).append("); \n\n");
                sb.append("void remove").append(className).append("(Long id); \n\n");
                break;
            case 4:
                sb.append(overrideAnnotation()).append("\n");
                sb.append("public ").append("List<").append(className).append("Entity> findAll() {").append("\n");
                sb.append("  return repository.findAll();").append("\n");
                sb.append(closeCurlyBracket()).append("\n\n");

                sb.append(overrideAnnotation()).append("\n");
                sb.append("public Optional<").append(className).append("Entity> findById(Long id) {").append("\n");
                sb.append("  return repository.findById(id);").append("\n");
                sb.append(closeCurlyBracket()).append("\n\n");

                sb.append(overrideAnnotation()).append("\n");
                sb.append("public ").append(className).append("Entity add").append(className).append("(").append(className).append("Entity ").append(firstLetterLowerCase(className)).append(") { \n");
                sb.append("  return repository.save(").append(firstLetterLowerCase(className)).append("); \n");
                sb.append(closeCurlyBracket()).append("\n\n");

                sb.append(overrideAnnotation()).append("\n");
                sb.append("public ").append(className).append("Entity update").append(className).append("(").append(className).append("Entity ").append(firstLetterLowerCase(className)).append(") { \n");
                sb.append("  return repository.save(").append(firstLetterLowerCase(className)).append("); \n");
                sb.append(closeCurlyBracket()).append("\n\n");

                sb.append(overrideAnnotation()).append("\n");
                sb.append("public void").append(" remove").append(className).append("(Long id) { \n");
                sb.append("  repository.deleteById(id);").append("\n");
                sb.append(closeCurlyBracket()).append("\n\n");
                break;
        }

        return sb.toString();
    }

    /**
     *
     * @param field
     * @return
     */
    public String getFieldType(String field) {
        return formatField(field.split(":")[0]);
    }

    /**
     *
     * @param field
     * @return
     */
    public String getFieldName(String field) {
        return formatField(field.split(":")[1]);
    }

    public String formatField(String field) {
        field = field.replace("\"", "");
        field = field.replace("\n", "");
        field = field.replaceAll("^ +| +$|( )+", "");
        field = field.trim();
        field = firstLetterLowerCase(field);
        return field;
    }

    /**
     *
     * @param fieldToFormat
     * @return
     */
    public String removeCamelCaseField(String fieldToFormat) {
        String[] fieldSplitted = fieldToFormat.split("(?<=[a-z])(?=[A-Z])");
        String fieldFormated = "";
        for (String field : fieldSplitted) {
            fieldFormated = fieldFormated + "_" + field;
        }
        fieldFormated = fieldFormated.substring(0, fieldFormated.lastIndexOf("_")) + "" + fieldFormated.substring(fieldFormated.lastIndexOf("_") + 1);
        return fieldFormated;
    }

    /**
     *
     * @param type
     * @param fieldName
     * @return
     */
    public String generateGetters(String type, String fieldName) {
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append(type).append(" get").append(firstLetterUpperCase(fieldName)).append("() { ").append("\n");
        sb.append("  return ").append(fieldName).append("; \n");
        sb.append("}");
        return sb.toString();
    }

    /**
     *
     * @param type
     * @param fieldName
     * @return
     */
    public String generateSetters(String type, String fieldName) {
        StringBuilder sb = new StringBuilder();
        sb.append("public void").append(" set").append(firstLetterUpperCase(fieldName)).append("(").append(type).append(" ").append(fieldName).append(") { \n");
        sb.append("  this.").append(fieldName).append(" = ").append(fieldName).append("; \n");
        sb.append("}");
        return sb.toString();
    }

    /**
     *
     * @param fieldName
     * @return
     */
    public String firstLetterUpperCase(String fieldName) {
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1).toLowerCase();
        return fieldName;
    }

    /**
     *
     * @param fieldName
     * @return
     */
    public String firstLetterLowerCase(String fieldName) {
        fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1).toLowerCase();
        return fieldName;
    }
    
    private String generateAutowiredAnnotation(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("@Autowired").append("\n");
        sb.append("private ").append(className).append("Repository repository; \n\n");
       
        return sb.toString();
    }

    /**
     *
     * @return
     */
    public String overrideAnnotation() {
        return "@Override";
    }

    /**
     *
     * @return
     */
    public String closeCurlyBracket() {
        return "}";
    }

    public Boolean saveToFile(StringBuilder sb, String path, String className) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/" + className + ".java"))) {
                writer.write(sb.toString());
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

}
