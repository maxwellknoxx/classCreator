package com.maxwell.classcreator.control;

import com.maxwell.classcreator.model.Json;
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
        if (json.getCreateServiceImpl()) {
            createServiceImpl(json);
        }
        if (json.getCreateRepository()) {
            createRepository(json);
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

        jsonText = jsonText.replaceAll("\\{", "").replace("}", "");

        json.setClassName(jsonText.split("\":")[0].replace("\"", ""));

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
        sb.append(endClass());
        
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

        return false;
    }

    /**
     * Class type 2
     *
     * @param json
     * @return
     */
    private Boolean createRepository(Json json) {
        StringBuilder sb = new StringBuilder();

        return false;
    }

    /**
     * Class type 3
     *
     * @param json
     * @return
     */
    private Boolean createService(Json json) {
        StringBuilder sb = new StringBuilder();

        return false;
    }

    /**
     * Class type 4
     *
     * @param json
     * @return
     */
    private Boolean createServiceImpl(Json json) {
        StringBuilder sb = new StringBuilder();

        return false;
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
                sb.append("import org.springframework.data.jpa.repository.JpaRepository;").append("\n");
                sb.append("import org.springframework.stereotype.Repository;").append("\n");
                break;
            case 3:
                sb.append("import java.util.List;").append("\n");
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
                sb.append("@Table(name = \"").append(className).append("\") \n");
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
                sb.append("public class ").append(className).append("Entity \\{").append("\n");
                break;
            case 1:
                sb.append("public class ").append(className).append("Model \\{").append("\n");
                break;
            case 2:
                sb.append("public interface ").append(className).append("Repository extends JpaRepository<").append(className).append("Entity, Long> \\{").append("\n");
                break;
            case 3:
                sb.append("public interface ").append(className).append("Service \\{").append("\n");
                break;
            case 4:
                sb.append("public class ").append(className).append("ServiceImpl implements ").append(className).append("Service \\{").append("\n");
                break;
            default:
                break;
        }

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
                        sb.append("private ").append(type).append(" ").append(fieldName).append("; \n");
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
        
        for(String field : json.getFields()) {
            type = getFieldType(field);
            fieldName = getFieldName(field);
            sb.append(generateGetters(type, fieldName)).append("\n\n");
            sb.append(generateSetters(type, fieldName)).append("\n\n");
        }

        return sb.toString();
    }

    /**
     * 
     * @param field
     * @return 
     */
    public String getFieldType(String field) {
        return field.split(":\"")[0].replace("\"", "");
    }

    /**
     * 
     * @param field
     * @return 
     */
    public String getFieldName(String field) {
        return field.split(":\"")[1].replace("\"", "");
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
    public String generateGetters(String type, String fieldName){
        StringBuilder sb = new StringBuilder();
        sb.append("public ").append(type).append(" get").append(firstLetterCamelCase(fieldName)).append("() \\{ ").append("\n");
        sb.append("return").append(fieldName).append("; \n");
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
        sb.append("public ").append(type).append(" set").append(firstLetterCamelCase(fieldName)).append("(").append(type).append(" ").append(fieldName).append(") \\{ \n");
        sb.append("this.").append(fieldName).append(" = ").append(fieldName).append("; \n");
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * 
     * @param fieldName
     * @return 
     */
    public String firstLetterCamelCase(String fieldName) {
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1).toLowerCase();
        return fieldName;
    }

    public String endClass(){
        return "}";
    }
    
    //private Boolean saveToFile(StringBuilder sb) {
//
  //      return false;
    //}

}