package lk.codelabs.sample.model;

public class FunctionalTest {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    private int id;
    private String code;
    private boolean result;

//    costructor
    public FunctionalTest(int id, String code, boolean result) {
        this.id = id;
        this.code = code;
        this.result = result;
    }

    @Override
    public String toString() {
        return "FunctionalTest{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", result=" + result +
                '}';
    }
}
