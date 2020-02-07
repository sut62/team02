<template>
 <v-app id="page">
  <v-container>
    <!-- <v-app-bar app color="blue darken-2" dark>
       <v-app-bar-nav-icon></v-app-bar-nav-icon> @click.stop="drawer = !drawer" 
      <v-toolbar-title>ระบบห้องสมุด</v-toolbar-title>
        <div class="flex-grow-1"></div> 

    </v-app-bar>-->
     <v-container>
      <div>
        <v-responsive>
          <v-flex>
            <v-card class="mx-auto" max-width="80%">
              <v-toolbar color="pink " dark flat>
                <v-toolbar-title>ระบบจัดเก็บรายชื่อหนังสือ</v-toolbar-title>
              </v-toolbar>

              <v-col cols="12">
                    <v-text-field
                            label="ชื่อหนังสือ"
                            name="name"
                            type="text"
                            v-model="booknames"
                            :rules="[(v) => !!v || 'Please fill in the information']"
                            required
                    ></v-text-field>
                  </v-col>

              <v-col cols="12">
                    <v-text-field
                            label="ชื่อผู้เขียน"
                            name="name"
                            type="text"
                            v-model="writternames"
                            :rules="[(v) => !!v || 'Please fill in the information']"
                            required
                    ></v-text-field>
                  </v-col>

                  <v-col cols="12">
                    <v-text-field
                            label="จำนวนหนังสือ"
                            name="name"
                            type="text"
                            v-model="amounts"
                            :rules="[(v) => !!v || 'Please fill in the information']"
                            required
                    ></v-text-field>
                  </v-col>

              <v-card-text>
                <v-form>
                  <v-row>
                    <v-col cols="12">
                      <v-select
                              label="ประเภทหนังสือ"
                              outlined
                              v-model="booktypeId"
                              :items="booktypes"
                              item-text="bookTypeName"
                              item-value="id"
                              :rules="[(v) => !!v || 'Item is required']"
                              required
                      ></v-select>
                    </v-col>
                  </v-row>

                     <v-row>
                    <v-col cols="12">
                      <v-select
                              label="ภาษา"
                              outlined
                              v-model="languageId"
                              :items="languages"
                              item-text="languageName"
                              item-value="id"
                              :rules="[(v) => !!v || 'Item is required']"
                              required
                      ></v-select>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col cols="12">
                      <v-select
                              label="หมวดหมู่หนังสือ"
                              outlined
                              v-model="bookcategoryId"
                              :items="bookcategorys"
                              item-text="bookCategoryName"
                              item-value="id"
                              :rules="[(v) => !!v || 'Item is required']"
                              required
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-form>
              </v-card-text>




               <v-card-actions>                 
                 <v-btn color="blue-grey darken-3" dark flat to="/home">กลับสู่หน้าหลัก</v-btn>   
                 <v-btn color="teal darken-4" dark flat to="/viewdocument">แสดงรายชื่อหนังสือ</v-btn>                       
                        <v-spacer></v-spacer>                
                  <v-btn color="red darken-4" dark flat @click="addDocument">บันทึกข้อมูล</v-btn>                      
              </v-card-actions>


              <v-snackbar v-model="snackbar">
                 {{ message}}
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

export default {
  name: "document",
  data() {
    return {
      snackbar: false,
      booknames: "",
      writternames: "",
      amounts:"",
      booktypeId:"",
      languageId: "",
      bookcategoryId: "",
      booktypes:[],
      languages:[],
      bookcategorys:[],
      message:''

    };
  },
 methods: {                                          //สร้าง method เพื่ออ้างอิงในการรับข้อมูล
            /* eslint-disable no-console */
             getBookname() {
              http
                 .get("/books")
                 .then(response => {
                 this.booknames = response.data;
                console.log(response.data);
                })
                .catch(e => {
                console.log(e);
              });
            },
            /* eslint-disable no-console */
             getWrittername() {
              http
                 .get("/writters")
                 .then(response => {
                 this.writternames= response.data;
                console.log(response.data);
                })
                .catch(e => {
                console.log(e);
              });
            },
            /* eslint-disable no-console */
             getAmount() {
              http
                 .get("/amounts")
                 .then(response => {
                 this.amounts= response.data;
                console.log(response.data);
                })
                .catch(e => {
                console.log(e);
              });
            },



    
            /* eslint-disable no-console */
            getBookType() {
              http
                 .get("/booktype")
                 .then(response => {
                 this.booktypes = response.data;
                console.log(response.data);
                })
                .catch(e => {
                console.log(e);
              });
            },
            /* eslint-disable no-console */
             getLanguage() {
              http
                 .get("/language")
                 .then(response => {
                 this.languages = response.data;
                console.log(response.data);
                })
                .catch(e => {
                console.log(e);
              });
            },
            /* eslint-disable no-console */
            getBookCategory() {
              http
                 .get("/bookcategory")
                 .then(response => {
                 this.bookcategorys = response.data;
                console.log(response.data);
                })
                .catch(e => {
                console.log(e);
              });
            },
            /* eslint-disable no-console */

            addDocument() {
          http
        .post(
          "/document/" + 
          this.booknames + "/" + 
          this.writternames + "/" + 
          this.amounts + "/" + 
          this.booktypeId + "/" + 
          this.languageId + "/" + 
          this.bookcategoryId,
          )

       .then(response => {
          console.log(response);
          this.message = "เพิ่มข้อมูลสำเร็จ";    
        })
        .catch(e => {
          console.log(e);
          this.message = "ไม่สามารถเพิ่มข้อมูลได้";
          })
          .finally(() =>{
                        this.snackbar = !this.snackbar;
                        this.reset();
          
        });
    },
        },

        mounted() { 
            this.getBookType();
            this.getLanguage();
            this.getBookCategory();
        }
    };
</script>

<style  scoped,lang="scss" scoped>
   
#page {
  background-image: url("https://scontent.fbkk8-3.fna.fbcdn.net/v/t1.15752-9/s2048x2048/85111636_650712959008558_5153470712316428288_n.jpg?_nc_cat=111&_nc_oc=AQlqwJ-Lba_sIjxxThN8dbQg_IbX0_r1IUx6W9jd6dEAO_uFDLXpU49Ks46rRMHcYDQ&_nc_ht=scontent.fbkk8-3.fna&oh=ed77fb7f68f9a30a7276eebe68d7ea80&oe=5EDA3D53");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}


</style>

