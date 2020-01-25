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
           
          <v-toolbar-title color="error">รายชื่อหนังสือ</v-toolbar-title>
        
    
       
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="10">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1"></v-data-table>
      </v-col>
    </v-row>

  
  
   
 
  </v-navigation-drawer>
</template>


    




<script>
import http from "../http-common"
export default {
  name: "ViewDocument",
  data() {
    return {
      headers: [
        { text: "Document ID", value: "id" },
        { text: "ชื่อหนังสือ", value: "bookName" },
        { text: "ชื่อผู้แต่ง", value: "writterName" },
        { text: "หมวดหมู่", value: "bookCategory.bookCategoryName" },
        { text: "ประเภทหนังสือ", value: "bookType.bookTypeName" },
        { text: "จำนวน/เล่ม", value: "amount" },
        { text: "ภาษา", value: "language.languageName" },
      ],
      items: []
    }
  },
  methods: {
    /* eslint-disable no-console */
    getDocument() {
      http.get("/document").then(results => {
        this.items = results.data
        console.log(results.data)
      }).catch(error => {
          console.log(error)
      })
    }
  },
  mounted() {
    this.getDocument();
  }
}
</script>