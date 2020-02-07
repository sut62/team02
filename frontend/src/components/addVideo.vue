<template>
  <v-app :style="{ backgroundImage: 'url(' + pic1 + ')' }">
  <v-container >

     <v-container>
      <div>
        <v-responsive>
          <v-flex>
            <v-card class="mx-auto" max-width="80%">
              <v-toolbar color="pink lighten-2" dark flat>
                <v-toolbar-title>ระบบจัดเก็บวีดีโอ</v-toolbar-title>

              </v-toolbar>

              <v-card-text>
                <v-form ref ="form">
                  <v-row>
                    <v-col cols="12">
                      <v-select
                              label="ชื่อบรรณารักษ์"
                              outlined
                              v-model="video.librarianid"
                              :items="librarian"
                              item-text="librarianName"
                              item-value="librarianid"
                              :rules="[(v) => !!v || 'กรุณาเลือกชื่อบรรณารักษ์']"
                              required
                      ></v-select>
                    </v-col>
                  </v-row>

                  <v-col cols="12">
                    <v-text-field
                            label="กรอกชื่อวีดีโอ"
                            name="videoName"
                            type="text"
                            v-model="video.videoName"
                            :rules="[(v) => !!v || 'กรุณากรอกชื่อวีดีโอ']"
                            required
                    ></v-text-field>
                  </v-col>

                  <v-col cols="12">
                    <v-text-field
                            label="กรอกรหัสวีดีโอ"
                            name="videoCode"
                            type="text"
                            v-model="video.videoCode"
                            :rules="[(v) => !!v || 'กรุณากรอกรหัสวีดีโอ']"
                            required
                    ></v-text-field>
                  </v-col>

                  <v-row>
                    <v-col cols="12">
                      <v-select
                              label="เลือกประเภทของวีดีโอ"
                              outlined
                              v-model="video.typeid"
                              :items="type"
                              item-text="type"
                              item-value="typeid"
                              :rules="[(v) => !!v || 'กรุณาเลือกประเภทของวีดีโอ']"
                              required
                      ></v-select>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col cols="12">
                      <v-select
                              label="เลือกหมวดหมู่ของวีดีโอ"
                              outlined
                              v-model="video.categoryid"
                              :items="category"
                              item-text="category"
                              item-value="categoryid"
                              :rules="[(v) => !!v || 'กรุณาเลือกหมวดหมู่ของวีดีโอ']"
                              required
                      ></v-select>
                    </v-col>
                  </v-row>

                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-btn color="red lighten-2" to="/Home">กลับสู่หน้าหลัก</v-btn>
                <v-btn color="blue lighten-3" to="/viewVideo">แสดงข้อมูลวีดีโอ</v-btn>
                 <v-spacer></v-spacer>
                <v-btn color="purple lighten-4" @click="saveVideo">บันทึกข้อมูล</v-btn>
              </v-card-actions>

              <v-snackbar v-model="snackbar">
                  {{ message }}
                <v-btn text color="primary" @click="snackbar = !snackbar">ปิด</v-btn>
              </v-snackbar>

            </v-card>
          </v-flex>
        </v-responsive>
      </div>
    </v-container>
  </v-container>
  </v-app>
</template>

<script>
import http from "../http-common";
// eslint-disable-next-line no-unused-vars
export default {
  name: "video",
  
  data() {
    return {
      pic1: "https://www.itl.cat/pics/b/32/323348_pastel-pink-wallpaper.png",
      snackbar: false,
      video: {
        librarianid: "",
        videoName:"",
        videoCode:"",
        typeid: "",
        categoryid: "",
      },
      librarian:[],
      type:[],
      category:[],
      message: ''
    };
  },
  methods: {
    /* eslint-disable no-console */
     /* eslint-disable */
     reset: function () {
            this.$refs.form.reset();
        },
    getCreateLibrarian() {
      http
        .get("/librarian")
        .then(response => {
          console.log(response.data);
          this.$forceUpdate();
          this.librarian = response.data;
          
        })
        .catch(e => {
          console.log(e);
        });
    },

    getCreatType() {
      http
        .get("/type")
        .then(response => {
          console.log(response.data);
          this.$forceUpdate();
          this.type = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    },

    getCreatCategory() {
      http
        .get("/category")
        .then(response => {
          this.$forceUpdate();
          this.category = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveVideo() {
      console.log(this.video.librarianid)
      console.log(this.video.videoName)
      console.log(this.video.videoCode)
      console.log(this.video.typeid)
      console.log(this.video.categoryid)
      http
        .post(
          ////addVideo/{librarianid}/{videoName}/{videoCode}/{typeid}/{categoryid}
            "/addVideo/" +
            this.video.librarianid +
            "/" +
            this.video.videoName +
            "/" +
            this.video.videoCode +
            "/" +
            this.video.typeid +
            "/" +
            this.video.categoryid ,
            this.video
        )
        .then(response => {
          console.log(response); 
          this.message = "เพิ่มข้อมูลสำเร็จ";
        })
        .catch(e => {
          console.log(e);
          this.message = "ไม่สามารถเพิ่มข้อมูลได้"
        })
        .finally(() => {
                    this.snackbar = !this.snackbar;
                    this.reset();
          });
    },
    refreshList() {
      this.getCreateLibrarian();
      this.getCreatType();
      this.getCreatCategory();
    }
  },
  mounted() {
    this.getCreateLibrarian();
      this.getCreatType();
      this.getCreatCategory();
  }
};
</script>
