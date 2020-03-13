<template>  <v-navigation-drawer  
      src="https://scontent.fbkk12-1.fna.fbcdn.net/v/t1.15752-9/s2048x2048/83019207_707637426433882_8480522138380402688_n.png?_nc_cat=106&_nc_oc=AQkWeQcUbFRfw3NgHAHI9otJ9B_7yAm2Ci8veXoTm9WPm1E_9aVeK8Mx-3hokTCWgZE&_nc_ht=scontent.fbkk12-1.fna&oh=39915d8268d69a13b49bfa3e86baeaf7&oe=5E942AFD"
      width="500%"
      height="700"
    >
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <br />
        <br /> 
          <v-toolbar-title color="error">ประวัติการคืนหนังสือ</v-toolbar-title>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="10">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
      <br /> <br /> 
        <v-card-actions>

                 
                  <v-btn color="blue-grey darken-3" dark flat to="/remand"><v-icon dark left>mdi-arrow-left</v-icon>กลับสู่หน้าคืนหนังสือ</v-btn>   
                 
                       
                        <v-spacer></v-spacer>

                 <v-btn color="pink" dark flat to="/home"><v-icon>mdi-home</v-icon>กลับสู่หน้าหลัก</v-btn>
                 
                  
              </v-card-actions>
     
      </v-col>
    </v-row>
  </v-navigation-drawer>
</template>
<script>
import http from "../http-common"
export default {
  name: "RemandData",
  data() {
    return {
      headers: [
        { text: "Remand ID", value: "id" },
        { text: "ชื่อสมาชิก", value: "borrow.member.name" },
        { text: "ชื่อหนังสือ", value: "borrow.document.bookName" },
        { text: "หมวดหมู่", value: "borrow.document.bookCategory.bookCategoryName" },
        { text: "ประเภทหนังสือ", value: "bookType.bookTypeName" },
        { text: "จำนวนที่ยืม/เล่ม", value: "borrow.numbers" },
        { text: "จำนวนที่คืน/เล่ม", value: "amount" },
        { text: "สถานะหนังสือ", value: "bookstatus.bookStatusName" },
        { text: "วันที่ยืม", value: "borrow.borrowDate" },
        { text: "วันที่ส่งคืน", value: "remanddate" },
      ],
      items: []
    }
  },
  methods: {
    /* eslint-disable no-console */
    getRemand() {
      http.get("/remand").then(results => {
        this.items = results.data
        console.log(results.data)
      }).catch(error => {
          console.log(error)
      })
    }
  },
  mounted() {
    this.getRemand();
  }
}
</script>