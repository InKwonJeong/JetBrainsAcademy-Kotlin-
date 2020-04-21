object PlayingField {
    object Size {
        var width = 0
        var height = 0

        fun changeSize(width: Int, height: Int) {
            if(width > 0)
                this.width = width
            else
                this.width = 0

            if(height > 0)
                this.height = height
            else
                this.height = 0
        }
    }
}