<template>
    <v-app>
        <v-card height="50%" width="50%" class = "mx-auto">
            <v-container>
                <v-card-title class="display-1">ระบบให้คะแนนการใช้งาน</v-card-title>
                <v-form ref="form">
                    <v-container>
                        <v-card hight="50%" wigth= "50%" class = "mx-auto">
                            <v-select 
                                 v-model = report.edlevel
                                 :items= Edlevel
                                 :rules= edlevelRules
                                 :label= "ระดับการศึกษา"
                                 required
                            ></v-select>
                        </v-card>
                    </v-container>
                </v-form>
            </v-container>
        </v-card>
    </v-app>
</template>

<script>

export default {
    data: () =>({
        snackbar: false,
        Edlevel: [],
        Status:[],
        Rating:[],
        rateup:{
            edlevel: null,
            status: null,
            email: null,
            suggestion: null,
            rating: null
        },
        suggestionRules: [v => !!v || "กรุณาระบุคำแนะนำ ! (หากไม่ต้องการระบุให้ใส่ - )"],
        edlevelRules: [ v=> !!v || "กรุณาเลือก"],
        statusRules: [v => !!v || "กรุณาเลือกสถานะ"],
        emailRules:[ v=> !!v || "โปรดระบุอีเมลของท่าน"],
        ratingRules: [ v=> !!v || "ระบุระดับคะแนนการใช้งานของท่าน"]
    }),
    methods: {
    reset: function () {
      this.$refs.form.reset();
        },
    },
    mounted() {

    }
    
}
</script>