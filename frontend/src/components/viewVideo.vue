<template>
<v-app :style="{ backgroundImage: 'url(' + pic1 + ')' }">
  <br>
  <br>
  <br>
  <v-card>
  <v-container>   
            <v-card-title>ข้อมูลวีดีโอ
              <div class="flex-grow-1" ></div>
      <v-text-field
        v-model="search"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
            </v-card-title>
                <v-card-text>
                    <v-data-table
                    :headers="headers"
                    :items="items"
                    item-key="id"
                    :search="search"
                    
                    class="elevation-1"
                    ></v-data-table>
                </v-card-text>  
                <v-btn color="red lighten-2" to="/Home">กลับสู่หน้าหลัก</v-btn> 
  </v-container>
  </v-card>
</v-app>
</template>


<script>
import http from "../http-common";
export default {
  name: "viewVideo",
  items: [],
  data() {
    return {
      pic1: "https://www.itl.cat/pics/b/32/323348_pastel-pink-wallpaper.png",
        search: "",
      headers: [
        {text: "รหัสวีดีโอ", value: "videoCode" } ,
        {text: "ชื่อวีดีโอ", value: "videoName" } ,
        {text: "ประเภทของวีดีโอ", value: "type.type" } ,
        {text: "หมวดหมู่ของวีดีโอ", value: "category.category" } ,
        {text: "ชื่อผู้บันทึก", value: "librarian.librarianName" }    
      ]
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล receipt ใส่ combobox
    getVideo() {
      http
        .get("/video")
        .then(response => {
          this.$forceUpdate(); 
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },

    
    /* eslint-disable no-console */
  },
  mounted() {
    this.getVideo();
  }
};
</script>

<style  scoped,lang="scss" scoped>
    .v-app-bar{
    margin-top: 1px;
    background-color:rgb(75, 73, 238)  
  }

  .v-form {
      width: 55%;
      background-color:rgb(250, 250, 250)
    }
    
  .v-btn{
      background-color:rgb(250, 250, 250)
  }

.basil {
  background-color: rgb(69, 91, 219) !important;
}
.basil--text {
  color: #edf5f2 !important;
}
</style>
