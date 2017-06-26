package sophia.com.ecommerce2.data;

/**
 * Created by archimede on 06/06/17.
 */

public class Category {
    private int mId;
    private String title;
    private String subTitle;
    private String imagePath;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public Category(){}

    public Category(String title, String subTitle, String imageView) {
        this.title = title;
        this.subTitle = subTitle;
        this.imagePath = imageView;
    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (title != null ? !title.equals(category.title) : category.title != null) return false;
        if (subTitle != null ? !subTitle.equals(category.subTitle) : category.subTitle != null)
            return false;
        return imagePath != null ? imagePath.equals(category.imagePath) : category.imagePath == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (subTitle != null ? subTitle.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getPhotoAtIndex(int index) throws ArrayIndexOutOfBoundsException{
        return imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
