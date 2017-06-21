public class URLInfo implements Comparable {
    private String link;
    private int level;
    private int count;

    public URLInfo(String link, int level) {
        this.link = link;
        this.level = level;
        this.count = 0;
    }

    public URLInfo(String link, int level, int count) {
        this.link = link;
        this.level = level;
        this.count = count;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int keywordCount) {
        this.count = keywordCount;
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }

    public int compareTo(Object obj) {
        if (!(obj instanceof URLInfo))
            return -1;
        URLInfo foundLink = (URLInfo) obj;
        return link.compareTo(foundLink.getLink());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof URLInfo))
            return false;
        return obj == this || ((URLInfo) obj).getLink().equals(link);
    }
}

