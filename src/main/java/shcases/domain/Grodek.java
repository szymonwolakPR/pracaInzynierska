package shcases.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Grodek.
 */

@Document(collection = "grodek")
public class Grodek implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("place")
    private String place;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("coords")
    private String coords;

    @Field("sign_type")
    private String sign_type;

    @Field("photo")
    private String photo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public Grodek place(String place) {
        this.place = place;
        return this;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public Grodek name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Grodek description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoords() {
        return coords;
    }

    public Grodek coords(String coords) {
        this.coords = coords;
        return this;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    public String getSign_type() {
        return sign_type;
    }

    public Grodek sign_type(String sign_type) {
        this.sign_type = sign_type;
        return this;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getPhoto() {
        return photo;
    }

    public Grodek photo(String photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Grodek grodek = (Grodek) o;
        if(grodek.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, grodek.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Grodek{" +
            "id=" + id +
            ", place='" + place + "'" +
            ", name='" + name + "'" +
            ", description='" + description + "'" +
            ", coords='" + coords + "'" +
            ", sign_type='" + sign_type + "'" +
            ", photo='" + photo + "'" +
            '}';
    }
}
