package com.chandan.mywalmart

import java.util.UUID

data class TodoItem(val id: UUID, var text: String, var isChecked: Boolean)