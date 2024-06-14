package space.besh.beka_back.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import space.besh.beka_back.enums.Status;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    Status status;
    Object data;
    String message;

    public Response(Object data) {
        this.status=Status.SUCCESS;
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
