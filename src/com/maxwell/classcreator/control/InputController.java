package com.maxwell.classcreator.control;

import com.maxwell.classcreator.model.Input;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Maxwell Knoxx - 06/2019
 */
public class InputController {

    private final static Logger LOGGER = Logger.getLogger(InputController.class.getName());

    /**
     *
     * @param input
     * @return
     */
    public Boolean generateFiles(Input input) {

        try {
            input = getInputFields(input);

            if (input.getCreateEntity()) {
                createEntity(input);
            }
            if (input.getCreateModel()) {
                createModel(input);
            }
            if (input.getCreateRepository()) {
                createRepository(input);
            }
            if (input.getCreateServiceImpl()) {
                createService(input);
                createServiceImpl(input);
            }
        } catch (Exception e) {
            FileController.createFile("Error -> InputController -> generateFiles" + e.getMessage(), "error", "txt");
        }

        return true;
    }

    /**
     *
     * @param input
     * @return
     */
    public Input getInputFields(Input input) {
        List<String> listFields = new ArrayList<>();
        String inputText = input.getText();

        String[] fields = inputText.split("\\n");

        if (!validateInput(fields)) {
            return null;
        }

        listFields.addAll(Arrays.asList(fields));

        input.setFields(listFields);

        return input;
    }

    /**
     * Class Type 0
     *
     * @param input
     * @return
     */
    private Boolean createEntity(Input input) {
        StringBuilder sb = new StringBuilder();

        sb.append(generateImports(0));
        sb.append(generateClassAnotation(0, input.getClassName()));
        sb.append(generateClassName(0, input.getClassName()));
        sb.append(generateFields(0, input));
        sb.append(generateGettersAndSetters(input));
        sb.append(closeCurlyBracket());

        System.out.println(sb.toString());
        FileController.createJavaFile(sb, input.getPath(), input.getClassName(), "Entity");

        return true;
    }

    /**
     * Class Type 1
     *
     * @param input
     * @return
     */
    private Boolean createModel(Input input) {
        StringBuilder sb = new StringBuilder();

        sb.append(generateImports(1));
        sb.append(generateClassAnotation(1, input.getClassName()));
        sb.append(generateClassName(1, input.getClassName()));
        sb.append(generateFields(1, input));
        sb.append(generateGettersAndSetters(input));
        sb.append(closeCurlyBracket());

        System.out.println(sb.toString());
        FileController.createJavaFile(sb, input.getPath(), input.getClassName(), "Model");

        return true;
    }

    /**
     * Class type 2
     *
     * @param input
     * @return
     */
    private Boolean createRepository(Input input) {
        StringBuilder sb = new StringBuilder();

        String className = input.getClassName();

        sb.append(generateImports(2));
        sb.append(generateClassAnotation(2, className));
        sb.append(generateClassName(2, className));
        sb.append(generateMethods(2, className));
        sb.append(closeCurlyBracket());

        System.out.println(sb.toString());
        FileController.createJavaFile(sb, input.getPath(), input.getClassName(), "Repository");

        return true;
    }

    /**
     * Class type 3
     *
     * @param input
     * @return
     */
    private Boolean createService(Input input) {
        StringBuilder sb = new StringBuilder();

        String className = input.getClassName();

        sb.append(generateImports(3));
        sb.append(generateClassAnotation(3, className));
        sb.append(generateClassName(3, className));
        sb.append(generateMethods(3, className));
        sb.append(closeCurlyBracket());

        System.out.println(sb.toString());
        FileController.createJavaFile(sb, input.getPath(), input.getClassName(), "Service");

        return true;
    }

    /**
     * Class type 4
     *
     * @param input
     * @return
     */
    private Boolean createServiceImpl(Input input) {
        StringBuilder sb = new StringBuilder();

        String className = input.getClassName();

        sb.append(generateImports(4));
        sb.append(generateClassAnotation(4, className));
        sb.append(generateClassName(4, className));
        sb.append(generateAutowiredAnnotation(className));
        sb.append(generateMethods(4, className));
        sb.append(closeCurlyBracket());

        System.out.println(sb.toString());
        FileController.createJavaFile(sb, input.getPath(), input.getClassName(), "ServiceImpl");

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
     * @param input
     * @return
     */
    public String generateFields(int classType, Input input) {
        StringBuilder sb = new StringBuilder();

        String type;
        String fieldName;

        switch (classType) {
            case 0:
                for (String field : input.getFields()) {
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
                for (String field : input.getFields()) {
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
     * @param input
     * @return
     */
    public String generateGettersAndSetters(Input input) {
        StringBuilder sb = new StringBuilder();

        String type;
        String fieldName;

        for (String field : input.getFields()) {
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

    private boolean validateInput(String[] fields) {

        try {
            for (String field : fields) {
                if (!field.contains(":")) {
                    return false;
                }
            }
        } catch (Exception e) {
            FileController.createFile("Error: InputController -> " + e.getMessage(), "error", "txt");
        }

        return true;
    }

}
