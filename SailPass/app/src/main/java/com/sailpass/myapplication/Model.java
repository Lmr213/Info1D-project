package com.sailpass.myapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Model {
    public static class DataSource {
        List<Ferry> data = new ArrayList<Ferry>();

        public DataSource() {
        }
        public DataSource(List<Ferry> data) {
            this.data = data;
        }
        public int count() { return this.data.size(); }
        public Ferry get(int i) { return this.data.get(i); }
    }

    public static class Ferry {
        public String name;
        public String price;
        public String priceChild;
        public String dateArrival;
        public String dateDeparture;
        public String timeArrival;
        public String timeDeparture;

        public Ferry(String name, String price, String priceChild ,String dateArrival, String timeArrival, String dateDeparture,  String timeDeparture) {
            this.name = name;
            this.price = price;
            this.priceChild = priceChild;
            this.dateArrival = dateArrival;
            this.dateDeparture = dateDeparture;
            this.timeArrival = timeArrival;
            this.timeDeparture = timeDeparture;
        }

        public String getDestination(){
            return name;
        }

        public String getPrice(){
            return price;
        }

        public String getDateArrival(){
            return dateArrival;
        }

        public String getDateDeparture(){
            return dateDeparture;
        }

        public String getPriceChild(){return priceChild;}

        public String getTimeArrival(){return timeArrival;}

        public String getTimeDeparture(){return timeDeparture;}
    }
}