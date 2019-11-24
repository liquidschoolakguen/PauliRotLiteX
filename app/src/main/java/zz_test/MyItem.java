package zz_test;

public class MyItem {
    private int imageid;
    private String imageheading="";
    public MyItem(int id,String title)
    {
        imageid=id;
        imageheading=title;
    }
    public int getImageid() {
        return imageid;
    }
    public String getImageheading() {
        return imageheading;
    }
    public void setImageheading(String imageheading) {
        this.imageheading = imageheading;
    }
    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
}