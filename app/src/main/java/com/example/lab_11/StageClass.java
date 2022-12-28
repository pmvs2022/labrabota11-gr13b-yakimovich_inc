package com.example.lab_11;

public class StageClass {

    public int ID;
    private String bookText;
    private String[] choicesText;
    private int numChoices;
    private String imageName;
    private String musicName;
    private int[] choicesLinks;

    public StageClass() {}

    public void setID(int id) {
        this.ID = id;
    }

    public void setText(String text) {
        this.bookText = text;
    }

    public void setNumChoices(int num){
        this.numChoices = num;
        this.choicesText = new String[numChoices];
        this.choicesLinks = new int[numChoices];
    }

    public void setChoices(String[] choices) {
        this.choicesText = choices;
    }

    public void setChoice(String choice, int index) {
        if(index >= numChoices){
            return;
        }
        this.choicesText[index] = choice;
    }

    public void setLinks(int[] links) {
        this.choicesLinks = links;
    }

    public void setLink(int link, int index) {
        if(index >= numChoices){
            return;
        }
        this.choicesLinks[index] = link;
    }

    public void setImage(String image) {
        this.imageName = image;
    }

    public void setMusic(String music) {
        this.musicName = music;
    }

    public String getText() {
        return bookText;
    }

    public int getNumChoices() {
        return numChoices;
    }

    public String getChoice(int index) {
        return choicesText[index];
    }

    public int getLink(int index) {
        return choicesLinks[index];
    }

    public String getImage()  {
        return imageName;
    }

    public String getMusic() {
        return musicName;
    }

}

