<template>
<v-app :style="{ backgroundImage: 'url(' + pic1 + ')' }">
  <br>
  <br>
  <br>
  <v-card class="mx-auto" width="50%">
  <v-container>   
            <v-card-title>ระบบแสดงและค้นหาการให้คะแนนการให้งาน
              <div class="flex-grow-1" ></div>
      <v-text-field
        v-model="search"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
            </v-card-title>
            <v-btn text to="/home">กลับสู่หน้าหลัก</v-btn> 
                <v-card-text>
                    <v-data-table
                    :headers="headers"
                    :items="RateUPX"
                    item-key="id"
                    :search="search"
                    
                    class="elevation-1"
                    ></v-data-table>
                </v-card-text>   
  </v-container>
  </v-card>
</v-app>
</template>

<script>
import RateUP from "../controller/RateUPController";
export default {
   data: () => ({
       pic1: "http://getwallpapers.com/wallpaper/full/e/9/e/1012715-pastel-wallpaper-1920x1080-for-windows.jpg",
       search: '',
       RateUPX:[],
       headers: [
      {
        text: "ลำดับ",
        value: "id"
      },
      {
        text: "สภานภาพ",
        value: "status"
      },
      {
        text: "ส่งโดย",
        value: "edlevel"
      },
      {
        text: "หัวเรื่อง",
        value: "email"
      },
      {
        text: "รายละเอียด",
        value: "suggestion"
      },
      {
        text: "คะแนนการใช้งาน",
        value: "rating"
      },
       {
        text: "วันที่รายงาน",
        value: "repDate"
      }
    ],
                }),
mounted() {
    RateUP.getRate().then(response => {
      response.data.forEach(snapshot => {
        this.RateUPX.push({
          id: snapshot.id,
          status: snapshot.status.status,
          edlevel: snapshot.edlevel.edlevel,
          email: snapshot.email,
          suggestion: snapshot.suggestion,
          repDate: snapshot.date,
          rating: snapshot.rating.rating
        });
      });
    });
    }
}
</script>

