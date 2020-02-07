/* eslint no-use-before-define:0 */
<template>
    <v-app id="page">
  <v-container>
 
    <!--<v-app-bar app color="blue darken-2" dark>
       <v-app-bar-nav-icon></v-app-bar-nav-icon>  @click.stop="drawer = !drawer" 
      <v-toolbar-title>ระบบห้องสมุด</v-toolbar-title>
        <div class="flex-grow-1"></div>
    </v-app-bar>-->
    <v-container>
      <div>
        <v-responsive>
          <v-flex>
            <v-card class="mx-auto" max-width="80%">
              <v-toolbar color="pink" dark flat>
                <v-toolbar-title>ระบบคืนหนังสือ</v-toolbar-title>
              </v-toolbar>

              <v-card-text>
                <v-form>
                  <v-row>
                    <v-col cols="12">
                      <v-select
                        label="ชื่อสมาชิก"
                        outlined
                        v-model="borrowId"
                        :items="borrows"
                        item-text="member.name"
                        item-value="borrowId"
                        :rules="[(v) => !!v || 'Item is required']"
                        required
                      ></v-select>
                    </v-col>
                  </v-row>

                  <v-row>
                    <v-col cols="12">
                      <v-select
                        label="ประเภทหนังสือ"
                        outlined
                        v-model="typeId"
                        :items="bookTypes"
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
                        label="ชื่อหนังสือ"
                        outlined
                        v-model="documentId"
                        :items="documents"
                        item-text="bookName"
                        item-value="id"
                        :rules="[(v) => !!v || 'Item is required']"
                        required
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-col cols="12">
                    <v-select
                      label="สภาพหนังสือ"
                      outlined
                      v-model="bookstatusId"
                      :items="bookStatus"
                      item-text="bookStatusName"
                      item-value="id"
                      :rules="[(v) => !!v || 'Item is required']"
                      required
                    ></v-select>
                  </v-col>
                  <v-card>
                    <v-text-field
                      label="จำนวนเล่ม"
                      type="text"
                      v-model="amount"
                      :rules="[(v) => !!v || 'Please fill in the information']"
                      required
                    ></v-text-field>
                  </v-card>
                </v-form>
              </v-card-text>

              
             

                 <v-card-actions>                 
                 <v-btn color="blue-grey darken-3" dark flat to="/home">กลับสู่หน้าหลัก</v-btn>   
                 <v-btn color="teal darken-4" dark flat to="/RemandData">แสดงประวัติการคืนหนังสือ</v-btn>                       
                        <v-spacer></v-spacer>                
                  <v-btn color="red darken-4" dark flat @click="addRemand">บันทึกข้อมูล</v-btn>                      
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
import http from "../http-common"
export default {
  name: "remand",
  data() {
    return {
      //เงื่อนไขในการบันทึกข้อมูล
      snackbar: false,
      typeId: "",
      borrowId: "",
      bookstatusId: "",
      documentId: "",
      
      documents: [],
      bookStatus: [],
      bookTypes: [],
      borrows: [],
      amount: "",
      message: ""
    }
  },
  methods: {
    //สร้าง method เพื่ออ้างอิงในการรับข้อมูล
    /* eslint-disable no-console */
    getBookingStatus() {
      //ดึงหลังบ้านเข้า combobox
      http
        .get("/bookstatus")
        .then(response => {
          this.bookStatus = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e) //ตัวตรวจจับ exception หรือ error
        })
    },
    getDocuments() {
      //ดึงหลังบ้านเข้า combobox
      http
        .get("/document")
        .then(response => {
          this.documents = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e) //ตัวตรวจจับ exception หรือ error
        })
    },
    /* eslint-disable no-console */
    getType() {
      //ดึงหลังบ้านเข้า combobox
      http
        .get("/booktype")
        .then(response => {
          this.bookTypes = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    /* eslint-disable no-console */
    getBorrow() {
      //ดึงหลังบ้านเข้า combobox
      http
        .get("/borrow")
        .then(response => {
          this.borrows = response.data
          console.log(response.data)
        })
        .catch(e => {
          console.log(e)
        })
    },
    /* eslint-disable no-console */
    addRemand() {
      http
        .post(
          "/remand/" + this.borrowId + "/" + this.typeId + "/" + this.documentId + "/" + this.bookstatusId + "/" + this.amount
          //"/remand/{borrowID}/{booktypeID}/{documentID}/{bookstatusID}/{amount}"
        )
        .then(response => {
          console.log(response)
          this.message = "เพิ่มข้อมูลสำเร็จ"
        })
        .catch(e => {
          console.log(e)
          this.message = "ไม่สามารถเพิ่มข้อมูลได้"
        })
        .finally(() => {
          this.snackbar = !this.snackbar
          // this.reset()
        })
    }
  },
  mounted() {
    //เรียกดึงข้อมูลจากหลังบ้าน
    this.getDocuments()
    this.getBookingStatus()
    this.getType()
    // console.log("asdasdasd")
    this.getBorrow()
  }
}
</script>
<style  scoped,lang="scss" scoped>
   
#page {

  background-image: url("https://scontent.fbkk8-3.fna.fbcdn.net/v/t1.15752-9/s2048x2048/85111636_650712959008558_5153470712316428288_n.jpg?_nc_cat=111&_nc_oc=AQlqwJ-Lba_sIjxxThN8dbQg_IbX0_r1IUx6W9jd6dEAO_uFDLXpU49Ks46rRMHcYDQ&_nc_ht=scontent.fbkk8-3.fna&oh=ed77fb7f68f9a30a7276eebe68d7ea80&oe=5EDA3D53");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}


</style>

