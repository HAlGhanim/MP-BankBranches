package com.example.mp_bankbranches.repository

import com.example.mp_bankbranches.models.Branch
import com.example.mp_bankbranches.models.BranchType

object BranchRepository {
    val branches = listOf(
        Branch(
            id = 1,
            name = "Headquarters Branch",
            type = BranchType.HEADQUARTERS,
            address = "Al-Shuhada St, Kuwait City 25843",
            phone = "1801801",
            hours = "8:30am - 3pm",
            location = "https://www.google.com/maps/place/NBK+Headquarters+Branch/@29.3778737,47.9894097,17z/data=!3m1!4b1!4m6!3m5!1s0x3fcf84983b2217a1:0xebb47292f199d957!8m2!3d29.3778691!4d47.9919846!16s%2Fg%2F11g7_fdswb?entry=ttu&g_ep=EgoyMDI1MDUyMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D",
            imageUri = "https://lh3.googleusercontent.com/gps-cs-s/AC9h4noFQP6MZqN72B45j2exXAcsj7mKsIVMbpdPusNMfKs5ITlD-OGnd1tBCRrdFtMa7Wm-G6wuoPQo9DJyTkkHgRCLP-IUEZMzle7APqUZKzMxLJORTu3W7Qxgw-DZaGiRylIxgVWn7A=s1360-w1360-h1020-rw"
        ),
        Branch(
            id = 2,
            name = "Jabriya Branch",
            type = BranchType.BRANCH,
            address = "Dahiya Center, 111 St, Jabriya 46301",
            phone = "1801801",
            hours = "8:30am - 3pm",
            location = "https://www.google.com/maps/place/NBK+-+National+Bank+of+Kuwait+%D8%A8%D9%86%D9%83+%D8%A7%D9%84%D9%83%D9%88%D9%8A%D8%AA+%D8%A7%D9%84%D9%88%D8%B7%D9%86%D9%8A%E2%80%AD/@29.3195186,48.0208527,17z/data=!3m1!4b1!4m6!3m5!1s0x3fcf9c43aa777737:0x74ff075b365d879f!8m2!3d29.3195139!4d48.0234276!16s%2Fg%2F11b6nkczjr?hl=en&entry=ttu&g_ep=EgoyMDI1MDUyMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D",
            imageUri = "https://lh3.googleusercontent.com/p/AF1QipPVVqYZQe3gt1e6kzDdkPShSctmRG3xYDylUps7=w390-h262-n-k-no"
        ),
        Branch(
            id = 3,
            name = "Surra Branch",
            type = BranchType.BRANCH,
            address = "Dahiya Center, 8 St, 45401",
            phone = "1801801",
            hours = "8:30am - 3pm",
            location = "https://www.google.com/maps/place/National+Bank+of+Kuwait/@29.313683,47.9991947,17z/data=!3m1!4b1!4m6!3m5!1s0x3fcf9da8afc540cb:0xc66856a7e457ea63!8m2!3d29.3136783!4d48.0017696!16s%2Fg%2F11kq8t66tj?entry=ttu&g_ep=EgoyMDI1MDUyMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D",
            imageUri = "https://lh3.googleusercontent.com/gps-cs-s/AC9h4npW4GLxnONKSZ-UWeSpqVCEB3AjKFFz4qCPNF9xSjaIqR5Hiwsdx1zBXmv6OPwE_xv8hImm41Yv9tKTYBhS8Q386rM89c_xIvopSuBVpEgiOiwVh1i1g12sL_P-_iA-BN81PRlR=w141-h141-n-k-no-nu"
        ),
        Branch(
            id = 4,
            name = "Trolley Saray Hotel ATM",
            type = BranchType.ATM,
            address = "Trolley Saray Hotel, 3648 Bin Misbah St, Kuwait City 15300",
            phone = "1801801",
            hours = "7am - 3pm",
            location = "https://www.google.com/maps/place/NBK+-+National+Bank+of+Kuwait+ATM+%D8%A8%D9%86%D9%83+%D8%A7%D9%84%D9%83%D9%88%D9%8A%D8%AA+%D8%A7%D9%84%D9%88%D8%B7%D9%86%D9%8A+ATM%E2%80%AD/@29.3867787,47.9872235,17z/data=!3m1!4b1!4m6!3m5!1s0x3fcf8581249fc7b7:0x616d76f354805144!8m2!3d29.3867741!4d47.9897984!16s%2Fg%2F11rg34my_s?hl=en&entry=ttu&g_ep=EgoyMDI1MDUyMS4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D",
            imageUri = "https://lh3.googleusercontent.com/gps-cs-s/AC9h4nq6mLMBrSm_t3UhhnA7LF-J3InOyee4lr6xV3_m7fXKrS4HNNB3aM_90-gDvawSgfWdqU4u3HwFKPWQiYG5kd8InIJEPNbi0P9lLj3bkQopAM2ncfjmHspZklh-5-BM3F9d8nk=s1360-w1360-h1020-rw"
        )
    )
}